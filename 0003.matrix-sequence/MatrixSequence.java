
/**
 * Google phone interview 
 * 
 * (?WTF? Write a code in phone interview, are you kidding me?)
 * 
 * Given a NxN matrix which contains all distinct 1 to n^2 numbers, 
 * write code to print sequence of increasing adjacent sequential numbers. 
 * Ex: 
 *  1 5 9 
 *  2 3 8 
 *  4 6 7 
 *
 * Should print: 
 *  6 7 8 9
 *
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class MatrixSequence {
	
	public static void main(String[] args) {
		
		MatrixSequence example = new MatrixSequence(
				new int[][]{
					new int[]{1,5,9},
					new int[]{2,3,8},
					new int[]{4,6,7}
				});
		
		System.out.println(example.sequence());
	}
	
	
	private String sequence() {
		String out = "";
		
		int[][] matrix = getMatrix();
		
		int max = Integer.MIN_VALUE, maxX = 0, maxY = 0;
		
		for (int k=0; k<matrix.length; k++) {
			for (int q=0; q<matrix.length; q++) {
				if (max < matrix[k][q]) {
					max = matrix[k][q];
					maxX = k;
					maxY = q;
				}
			}
		}
				
		boolean found = false;
		
		do {
			found = false;
			out = " ".concat(String.valueOf(max)).concat(out);

			for (int z=-1; z<=1; z = z+2) {

				int value = (maxX+z >= 0 && maxX+z < matrix.length) ?
						matrix[maxX+z][maxY] : Integer.MAX_VALUE;
				if (max - value == 1) {
					max = value;
					maxX = maxX+z;
					found = true;
					break;
				}
				
				value = (maxY+z >= 0 && maxY+z < matrix.length) ?
						matrix[maxX][maxY+z] : Integer.MAX_VALUE;
				if (max - value == 1) {
					max = value;
					maxY = maxY+z;
					found = true;
					break;
				}
			}
			
		} while (found);
		
		return out.trim();
	}


	public MatrixSequence(int[][] matrixIn) {
		setMatrix(matrixIn);
	}
	
	private int[][] matrix;

	private int[][] getMatrix() {
		return matrix;
	}

	private void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

}
