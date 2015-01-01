/**
 * Google In-Person interview task
 * 
 * Given an integer array, adjust each integers so that the difference of every
 * adjacent integers are not greater than a given number target.
 * 
 * If the array before adjustment is A, the array after adjustment is B, you
 * should minimize the sum of |A[i]-B[i]|
 * 
 * You can assume each number in the array is a positive integer and not greater
 * than 100
 * 
 * 
 * Given [1,4,2,3] and target=1, one of the solutions is [2,3,2,3], the
 * adjustment cost is 2 and it's minimal. Return 2.
 * 
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class AdjacentNumbers {

	public static void main(String[] args) {

		/* good case */
		int[] array = new int[] { 1, 4, 2, 3 };

		System.out.println(adjustAdjacent(array, 1));

		for (int i : array) {
			System.out.print(i);
			System.out.print(" ");

		}

		System.out.println();

		/* bad case */
		array = new int[] { 1, 4, 6, 3, 7, 4, 9, 0 };

		System.out.println(adjustAdjacent(array, 1));

		for (int i : array) {
			System.out.print(i);
			System.out.print(" ");

		}

	}

	private static int adjustAdjacent(int[] input, int target) {
		int minCost = 0;

		for (int k = 2; k <= input.length - 2; k = k + 2) {
			/* calculate pairs difference */
			int forward = Math.abs(input[k] - input[k + 1]);
			int backward = Math.abs(input[k - 2] - input[k - 1]);

			/* both pairs are good enough */
			if (forward <= target && backward <= target) {
				continue;
			}

			/* apply adjustments for all possible cases */
			if (forward <= target) {
				minCost += adjustLeft(input, k, target);
			} else if (backward <= target) {
				minCost += adjustRight(input, k, target);
			} else {
				/* worst case, we should check middle difference 
				 * to get minimal cost 
				 */
				if (input[k] - input[k-1] > 0) {
					minCost += adjustRight(input, k, target);
					minCost += adjustLeft(input, k, target);
				} else {
					minCost += adjustLeft(input, k, target);	
					minCost += adjustRight(input, k, target);
				}
			}
		}

		return minCost;
	}

	/* adjustment from right to left */
	private static int adjustLeft(int[] input, int k, int target) {
		int cost = 0;

		/* pair in the middle */
		while (Math.abs(input[k - 1] - input[k]) > target) {
			input[k - 1] = input[k - 1] > input[k] 
					? --input[k - 1] 
					: ++input[k - 1];
			cost++;
		}

		/* left pair */
		while (Math.abs(input[k - 2] - input[k - 1]) > target) {
			input[k - 2] = input[k - 2] > input[k - 1] 
					? --input[k - 2] 
					: ++input[k - 2];
			cost++;
		}

		return cost;
	}

	/* adjustment from left to right */
	private static int adjustRight(int[] input, int k, int target) {
		int cost = 0;

		/* pair in the middle */
		while (Math.abs(input[k - 1] - input[k]) > target) {
			input[k] = input[k - 1] > input[k] 
					? ++input[k] 
					: --input[k];
			cost++;
		}

		/* right pair */
		while (Math.abs(input[k + 1] - input[k]) > target) {
			input[k+1] = input[k + 1] > input[k] 
					? --input[k+1] 
					: ++input[k+1];
			cost++;
		}

		return cost;
	}
}
