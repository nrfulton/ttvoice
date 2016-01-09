package org.nfulton.pgh.truetime

case class BusInfo(route: String, estimatedArrivalCountdown: Option[Int], scheduledArrivalTime: String)

trait BusInfoFormatter extends (Seq[BusInfo] => String)

object CSVOutputFormatter extends BusInfoFormatter {
  override def apply(infos: Seq[BusInfo]): String =
    infos.map(info => info.route + "," + info.estimatedArrivalCountdown + "," + info.scheduledArrivalTime).mkString("\n")
}

object SpeechTranscriptOutputFormatter extends BusInfoFormatter {
  override def apply(infos: Seq[BusInfo]): String =
    if(infos.length == 0) {
      "There are no incoming buses."
    }
    else {
      val preface =
        if(infos.length == 1) s"There is a bus arriving soon.  "
        else s"There are ${infos.length} buses arriving soon.  "

      val theSchedule = infos
        .sortBy(info => info.estimatedArrivalCountdown.getOrElse(10000))
        .map(str(_)).mkString(".\n")

      preface + theSchedule
    }

  private def str(info: BusInfo) = info.estimatedArrivalCountdown match {
    case Some(i) => "A " + info.route + " is arriving in " + i + " minutes. Bus was scheduled to arrive at " + info.scheduledArrivalTime + "."
    case None => "A " + info.route + " might be arriving but estimated arrival is unknown. Bus was scheduled to arrive at " + info.scheduledArrivalTime + "."
  }
}
