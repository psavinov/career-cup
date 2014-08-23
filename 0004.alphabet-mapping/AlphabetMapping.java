/**
 * 
 * Facebook phone interview
 * 
 * (?WTF? another coding task in phone interview...world is going crazy)
 * 
 * Given a mapping of alphabets to integers as follows:
 * 
 * 1 = A 2 = B 3 = C ... 26 = Z
 * 
 * Given any combination of the mapping numbers as string, return the number of
 * ways in which the input string can be split into sub-strings and represented
 * as character strings. For e.g. given
 * 
 * "111" -> "AAA", "AK", "KA" -> 3 Valid combinations are ({1,1,1},
 * {1,11},{11,1}) = 3
 * 
 * "11" -> "AA", "K" -> 2 Valid combinations are ({1,1},{11}) = 2
 * 
 * "123" -> "ABC", "LC", "AW" -> 3 Valid combinations are
 * ({1,2,3},{1,23},{12,3}) = 3
 * 
 * You don't have to return all the mappings, only the number of valid mappings.
 * 
 * BTW, i found the same task in Amazon Tasks:
 * 
 * A file of encoded message contains only numbers. Original message contains
 * only lowercase letters and spaces. So character ‘a’ is mapped to 1 ‘b’ to 2
 * and so on till ‘z’ is mapped to 26. Given an input of numbers find out the
 * number of ways you can decode it in original message. Eg. 123 can be decoded
 * in 3 ways as 'abc', 'lc' or 'aw'
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class AlphabetMapping {

	public static void main(String[] args) {

		System.out.println(countMappings("111111"));
		System.out.println(countMappings("1221"));
		System.out.println(countMappings("123"));
	}

	private static int countMappings(String string) {
		int count = 0;

		for (int k = 0; k < string.length() - 1; k++) {
			int part = Integer.parseInt(string.substring(k, k + 2));
			if (part > 0 && part <= ALPHABET) {
				count++;
				count += subCount(string, k);
			}
		}

		return ++count;
	}

	private static int subCount(String string, int k) {
		int subCount = 0;
		for (int q = k + 2; q < string.length() - 1; q++) {
			int near = Integer.parseInt(string.substring(q, q + 2));
			if (near > 0 && near <= ALPHABET) {
				subCount++;
				subCount += subCount(string, q);
			}
		}
		
		return subCount;
	}

	private static final int ALPHABET = 26;

}
