# ttvoice

Produces information about PGH's realtime bus schedule that can be sent to a voice synthesizer.

My use case: runs every couple of minutes in the morning when I'm getting close to leaving. I pipe the output to festival.

It's particularly nice to get the estimated arrival time *and also* the time that the bus is/was supposed to arrive.
That info is included in the XML files that Port Authority hands down via TrueTime, but isn't displayed in their est. arrival time tables.

See http://nfulton.org/software/truetime.html for more information.
