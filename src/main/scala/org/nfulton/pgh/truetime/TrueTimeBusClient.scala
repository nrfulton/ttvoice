package org.nfulton.pgh.truetime

import scala.xml.{NodeSeq, Elem, XML}

/**
  * TrueTime Bus Client
  * @author Nathan Fulton
  */
object TrueTimeBusClient {
  def apply(stop: Int, route: Option[String]) : Seq[BusInfo] = {
    val xml = route match {
      case Some(route) => filterByRoute(downloadPredications(stop), route)
      case None => downloadPredications(stop)
    }
    (xml \\ "pre").map(parseStopInfo(_))
    //.filter(_.route == "71D")
  }

  private def downloadPredications(stop: Int) = XML.load(predictionsURL(stop))
  private def predictionsURL(stop: Int) =
    s"http://truetime.portauthority.org/bustime/eta/getStopPredictionsETA.jsp?route=all&stop=${stop}"

  private def filterByRoute(xml: Elem, route: String) = xml \\ "stop" filter(node => (node \\ "rn").last.text == route)

  //@todo improve this function.
  private def parseStopInfo(node: NodeSeq) = {
    val route = (node \\ "rn").last.text
    val rawTimer = (node \\ "pt").last.text
    val scheduled = (node \\ "nextbusonroutetime").last.text

    val timer = {
      try {
        Some(Integer.parseInt(rawTimer))
      }
      catch {
        case _:Throwable => None
      }
    }

    BusInfo(route, timer, scheduled)
  }
}
