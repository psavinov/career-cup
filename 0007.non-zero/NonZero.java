/**
 * Facebook phone interview task
 * 
 * Write an algorithm that brings all nonzero elements to the left of the array,
 * and returns the number of nonzero elements.
 * 
 * Example input: 
 * [ 1, 0, 2, 0, 0, 3, 4 ] 
 * 
 * Example output: 
 * [1, 4, 2, 3, 0, 0, 0] 4
 * 
 * The algorithm should operate in place, i.e. shouldn't create a new array. The
 * order of nonzero elements does not matter
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class NonZero {

	public static void main(String[] args) {

		int[] array = new int[]{1, 0, 2, 0, 0, 3, 4};
		processNonZeros(array);
		
		int nze = 0;
		System.out.print("[");
		for (int k=0; k<array.length; k++) {
			System.out.print(array[k]);
			if (k<array.length-1) {
				System.out.print(", ");
			}
			if (array[k] != 0) {
				nze++;
			}
		}
		
		System.out.print("] ");
		System.out.println(nze);
		

	}

	private static void processNonZeros(int[] input) {
		
		int q = 0;
		for (int k=0; k<input.length;k++) {
			int current = input[q];
			
			if (current != 0) {
				q++;
			} else {
				if (input[k] != 0) {
					input[q] = input[k];
					input[k] = 0;
					q++;
				}
			}
		}
	}

}
