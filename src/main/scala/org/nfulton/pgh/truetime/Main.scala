package org.nfulton.pgh.truetime

import java.io.{File, FileWriter, BufferedWriter}

import sys.process._

/**
  * Created by nfulton on 12/8/15.
  */
object Main {
  def main(args: Array[String]):Unit = {
    val stopNumber = args(0)
    val route      = if(args.length == 2) Some(args(1)) else None 
    
    val text = 
      SpeechTranscriptOutputFormatter(TrueTimeBusClient(stopNumber.toInt, route))

    println(text);

//    if(args.length == 1) {
//      //Write transcript to file.
//      val transcriptFile = new File("/tmp/truetime")
//      val handle         = new BufferedWriter(new FileWriter(transcriptFile))
//      handle.write(text)
//      handle.close()
//      
//      "festival --tts /tmp/truetime"!
//      
//      transcriptFile.delete()
//    }
//    else {
//      println(text)
//    }
  }
}
