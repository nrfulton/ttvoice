import org.nfulton.pgh.truetime.{TrueTimeBusClient, SpeechTranscriptOutputFormatter}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by nfulton on 12/8/15.
  */
class MorewoodTests extends FlatSpec with Matchers {
  "1177" should "get all results" in {
    println(SpeechTranscriptOutputFormatter(TrueTimeBusClient(1177, None)))
  }

  it should "filter to D" in {
    println(TrueTimeBusClient(1177, Some("71D")))
  }
}
