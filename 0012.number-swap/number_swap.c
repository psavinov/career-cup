/*
 * number_swap.c
 *
 * Google interview task
 *
 * Given a number M (N-digit integer) and K-swap
 * operations(a swap operation can swap 2 digits),
 * devise an algorithm to get the maximum possible integer?
 *
 * Examples:
 * M = 132 K = 1 output = 312
 * M = 132 K = 2 output = 321
 * M = 7899 k = 2 output = 9987
 * M = 8799 and K = 2 output = 9987
 *
 *  Created on: Oct 5, 2014
 *      Author: Pavel Savinov // savinovpa@gmail.com
 */

#include<stdio.h>
#include<stdlib.h>

unsigned long get_max_swapped(unsigned long number, short swap_count);
short swap(char* array, short count, short idx);

int main(int argc, char** argv) {

	printf("%d (%d) -> %lu\n", 132, 1, get_max_swapped(132, 1));
	printf("%d (%d) -> %lu\n", 132, 2, get_max_swapped(132, 2));
	printf("%d (%d) -> %lu\n", 7899, 2, get_max_swapped(7899, 2));
	printf("%d (%d) -> %lu\n", 8799, 2, get_max_swapped(8799, 2));

	return 0;
}

unsigned long get_max_swapped(unsigned long number, short swap_count) {

	/* count chars in number */
	short count = 0, k = 0;
	unsigned long n = number;
	while (n != 0) {
		n /= 10;
		++count;
	}

	/* convert buffer to chars array */
	char buffer[count];
	sprintf(buffer, "%lu", number);

	/* sort array, but only swap_count times */
	short index = 0;
	for (k = 0; k < swap_count; k++) {
		index = swap(buffer, count, index);
	}

	return atoi(buffer);
}

/* swap routine */
short swap(char* array, short count, short idx) {

	short mx = idx, mn = idx, i = 0;

	/* looking for the last max index, from previous max+1 */
	for (i = idx; i < count; i++) {
		if ((int) array[i] >= (int) array[mx]) {
			mx = i;
		}
	}

	/* looking for the first min index according to max */
	for (i = 0; i < count; i++) {
		if ((int) array[i] < (int) array[mn] && i<mx) {
			mn = i;
		}
	}

	/* swapping last max and first min */
	char temp = array[mn];
	array[mn] = array[mx];
	array[mx] = temp;

	return ++mn;
}

