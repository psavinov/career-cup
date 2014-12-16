#!/usr/bin/perl
#
# Booking.com interview task
#
# For input strings like:
#
#    one hundred twenty five
#    two
#    forty seven
#    thirty six thousands two hundred and twelve
#
# Sort numbers in descending orders and produce output:
#
#    thirty six thousands two hundred and twelve
#    one hundred twenty five
#    forty seven
#    two
#
# Pavel Savinov // savinovpa@gmail.com

#multipliers base
my %mupliplier = (
	'one', 1, 'two', 2, 'three', 3, 'four', 4, 'five', 5, 
	'six', 6, 'seven', 7, 'eight', 8, 'nine', 9, 'ten', 10,
	'eleven', 11, 'twelve', 12, 'thirteen', 13, 'fourteen', 14, 
	'fithteen', 15,	'sixteen', 16, 'seventeen', 17, 
	'eighteen', 18, 'nineteen', 19, 'twenty', 20, 
	'thirty', 30, 'forty', 40, 'fifty', 50, 'sixty', 60, 
	'seventy', 70, 'eighty', 80, 'ninety', 90, 'hundred', 100, 
	'thousand', 1000, 'million', 1000000, 'billion', 1000000000
);

my %numbers;

# reading numbers from STDIN
@input = <STDIN>;
foreach $line (@input) {
	my $number = 0;
	my $mult = 0;
	
	# begin to parse line from the last word
	@words = reverse(split(/\s/, $line));
	for ($k=0; $k<@words; $k++) {
		$word = $words[$k];
		if ($word =~ /s[\r\n]*$/) {
			chop($word);
		}
		
		if ($word eq 'and') {
			next;
		}
		
		# multiplier found
		if ($mupliplier{$word} >= 100) {
			$mult = $mult == 0 ? $mupliplier{$word} : 
				$mult*$mupliplier{$word};
			next;
		} 
		
		# add number with multiplier o without it
		$number += $mult == 0 ? $mupliplier{$word} : 
			$mult*$mupliplier{$word};
		
		# if not tenths or and, set multiplier value to zero
		if ($k<@words-1 && 
				$words[$k+1] ne 'and' &&
				!($words[$k+1] =~ /.*ty/)) {
			$mult = 0;
		}

	}
	
	# add number to the output hash
	$numbers{$line} = $number;
}

# reverse sorting to begin from the highest number
my @keys = reverse sort { $numbers{$a} <=> $numbers{$b} } keys(%numbers);

foreach my $key (@keys) {
    print $key;
}