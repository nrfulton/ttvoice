#!/usr/bin/perl
use strict;
use warnings;
use CGI;
use Scalar::Util::Numeric qw(isint);

my $c = "java -jar path/to/truetime/jar/tt.jar";

my $q = CGI->new;
my $stops_csv = $q->param('stops');

my @requested_stops = split /,/, $stops_csv;

print qq(Content-type: text/plain\n\n);

foreach my $stop (@requested_stops) {
  die 'security' unless(isint($stop));
  print "Info for stop number $stop.\n";
  print `$c $stop`;
  print "\n";
}

exit 0;
__END__

