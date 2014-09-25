/*
 * Bloomberg LP interview task
 *
 * Write a method that takes an int as input
 * and outputs an int with the digits of the
 * input in reverse, i.e. 12345 -> 54321.
 *
 * Pavel Savinov // savinovpa@gmail.com
 *
 */

#include<stdio.h>

int main(void) {

	printf("%d\n", reverse_number(12345));
	printf("%d\n", reverse_number(32478));
	printf("%d\n", reverse_number(289729352));

	return 0;
}

int reverse_number(int input) {
	int result = 0;

	do {
		int part = input % 10;
		input /= 10;
		result = (input > 0) ?
				(result+part)*10 :
					result+part;
	} while (input != 0);

	return result;
}
