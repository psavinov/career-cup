/**
 * 
 * CareerCup task from Google tech interview:
 * 
 * Given a string (for example: "a?bc?def?g"), write a program to generate all the possible strings by replacing ? with 0 and 1. 
 *	Example: 
 *	Input : a?b?c? 
 *	Output: a0b0c0, a0b0c1, a0b1c0, a0b1c1, a1b0c0, a1b0c1, a1b1c0, a1b1c1.
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class StringReplacer {
	
	private String input;
	
	public StringReplacer(String in) {
		this.input = in != null ? in : "?";
	}
	
	public void replace() {
		int count = 0;
		
		for (char c : this.input.toCharArray()) {
			if (c == '?') {
				count++;
			}
		}
		

		for (int z=0, replacer = (1 << count)-1; z<(1 << count); z++, replacer--) {
			String replacerString = Integer.toBinaryString(replacer);
			while (replacerString.length()<count) {
				replacerString = "0"+replacerString;
			}
				
			int c=0;
			char[] chars = this.input.toCharArray();
			for (int p=0; p<chars.length; p++) {
				if (chars[p] == '?') {
					chars[p] = replacerString.charAt(c++);
				}
			}
			
			System.out.println(new String(chars));
		}
		
	}
	
	public static void main(String[] args) {
		StringReplacer replacer = new StringReplacer("a?b?c?");
		
		replacer.replace();
		
	}

}
