#!/usr/bin/perl
use strict;
use warnings;
use Getopt::Long;

my $c = "java -jar ../target/scala-2.11/TrueTimeVoice-assembly-0.1-SNAPSHOT.jar ";

my $stop;
my $route;

GetOptions(
  "stop=i" => \$stop,
  "route=s" => \$route
);

if($route) {
  print `$c $stop $route`;
}
else {
  print `$c $stop`;
}

exit 0;
__END__

