#!/usr/bin/perl
#
# Google interview task
#
# How many occurrences of a given search word can you find
# in a two-dimensional array of characters given that the
# word can go up, down, left, right, and around 90 degree bends?
#
# Ex: Count of occurrences of SNAKES
#
# S N B S N
# B A K E A
# B K B B K
# S E B S E
#
# The answer is 3.
#
# Pavel Savinov // savinovpa@gmail.com
#

my $word = "SNAKES";

my @chars = (
	[ 'S', 'N', 'B', 'S', 'N' ],
	[ 'B', 'A', 'K', 'E', 'A' ],
	[ 'B', 'K', 'B', 'B', 'K' ],
	[ 'S', 'E', 'B', 'S', 'E' ]
);

#---------------------------------------------------------
# length of main array and first character of word
my $first    = substr( $word, 0, 1 );
my $x_length = @chars;
my $count    = 0;
my $y_length;

# iterate over all arrays because word
# can occur at any index
for ( my $i = 0 ; $i < $x_length ; $i++ ) {
	$y_length = @{ $chars[$i] };
	for ( my $k = 0 ; $k < $y_length ; $k++ ) {
		# start search from first letter
		$count += &find_way( $i, $k, qw(root), 0 );
	}
}

# final output of number
print $count, " occurences of '", $word, "' found!\n";

# routine to find the word
sub find_way {
	local $cnt = 0;

	my $x         = shift;
	my $y         = shift;
	my $direction = shift;
	my $start     = shift;
	my $char      = substr( $word, $start, 1 );

	if ( $direction eq 'right' ) {
		$x++;
	}
	elsif ( $direction eq 'down' ) {
		$y++;
	}
	elsif ( $direction eq 'left' ) {
		$x--;
	}
	elsif ( $direction eq 'up' ) {
		$y--;
	}

	my $c = $chars[$x][$y];

	if ( $c eq $char ) {
		if ( $start == length($word) - 1 ) {
			$cnt++;
		}
		else {

			if ( $x < $x_length - 1 ) {
				$cnt += &find_way( $x, $y, qw(right), $start + 1 );
			}

			if ( $y < $y_length - 1 ) {
				$cnt += &find_way( $x, $y, qw(down), $start + 1 );
			}

			if ( $x > 0 ) {
				$cnt += &find_way( $x, $y, qw(left), $start + 1 );
			}

			if ( $y > 0 ) {
				$cnt += &find_way( $x, $y, qw(up), $start + 1 );
			}
		}
	}

	return $cnt;
}
