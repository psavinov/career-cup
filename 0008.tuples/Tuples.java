import java.util.Arrays;

/**
 * Google Phone Interview Task
 * 
 * There is an array of 3-tuple, in the form of (a, 1, 5). The first element in
 * the tuple is the id, the second and third elements are both integers, and the
 * third is always larger than or equal to the second. Assume that the array is
 * sorted based on the second element of the tuple. Write a function that breaks
 * each of the 3-tuple into two 2-tuples like (a, 1) and (a, 5), and sort them
 * according to the integer.
 * 
 * E.g. given (a, 1, 5), (b, 2, 4), (c, 7, 8), output (a, 1), (b, 2), (b, 4),
 * (a, 5), (c, 7), (c, 8).
 * 
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class Tuples {

	/* three-tuple, transformable to two-tuple array */
	class ThreeTuple {
		
		public ThreeTuple(char c, int s, int e) {
			setId(c);
			setStart(s);
			setEnd(e);
		}
		
		private char id;
		private int start;
		private int end;

		public char getId() {
			return id;
		}

		public void setId(char id) {
			this.id = id;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}
		
		public TwoTuple[] getTwoTuples() {
			return new TwoTuple[]{
					new TwoTuple(getId(), getStart()),
					new TwoTuple(getId(), getEnd())
			};
		}

	}
	
	/* comparable two-tuple */
	class TwoTuple implements Comparable<TwoTuple> {
		
		public TwoTuple(char i, int val) {
			setId(i);
			setValue(val);
		}
		
		private char id;
		private int value;
		
		public char getId() {
			return id;
		}

		public void setId(char id) {
			this.id = id;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		@Override
		public int compareTo(TwoTuple o) {
			if (getValue() < o.getValue()) {
				return -1;
			} else if (getValue() > o.getValue()) {
				return 1;
			} else {
				return 0;
			}
		}
		
		@Override
		public String toString() {
			StringBuffer b = new StringBuffer("(")
			.append(getId()).append(",")
			.append(getValue()).append(")");
			
			return b.toString();
		}
		
		
	}

	public static void main(String[] args) {
		Tuples parent = new Tuples();
		ThreeTuple[] threeTuples = new ThreeTuple[]{
				parent.new ThreeTuple('a', 1, 5),
				parent.new ThreeTuple('b', 2, 4),
				parent.new ThreeTuple('c', 7, 8)
		};
		
		TwoTuple[] twoTuples = new TwoTuple[threeTuples.length*2]; 
		int k = 0;
		for (ThreeTuple t : threeTuples) {
			TwoTuple[] two = t.getTwoTuples();
			twoTuples[k++] =  two[0];
			twoTuples[k++] =  two[1];
		}
		
		Arrays.sort(twoTuples);
		
		for (TwoTuple t : twoTuples) {
			System.out.println(t.toString());
		}

	}

}
