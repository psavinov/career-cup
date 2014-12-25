import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Google In-Person interview task
 * 
 * Question was "Given a pattern and a string input - find if the 
 * string follows the same pattern and return 0 or 1. 
 * 
 * Examples: 
 *  1) Pattern : "abba", input: "redbluebluered" should return 1. 
 *  2) Pattern: "aaaa", input: "asdasdasdasd" should return 1. 
 *  3) Pattern: "aabb", input: "xyzabcxzyabc" should return 0.
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class PatternMatch {
	
	public static void main(String[] args) {
		System.out.println(checkPattern("abba","redbluebluered"));
		System.out.println(checkPattern("aaaa","asdasdasdasd"));
		System.out.println(checkPattern("aabb","xyzabcxzyabc"));
	}
	
	public static byte checkPattern(String patternString, String input) {
		StringBuffer patternBuffer = new StringBuffer();
		
		/* check input and pattern strings */
		if (patternString == null || input == null) {
			throw new IllegalArgumentException();
		}
		
		/* pattern character array */
		List<Character> chars = new ArrayList<Character>();
		for (char c : patternString.toCharArray()) {
			
			if (!chars.contains(c)) {
				/* new character found, append new group */
				patternBuffer.append("(.+)");
				chars.add(c);
			} else {
				/* looking for unique sequence by number */
				patternBuffer.append("\\")
					.append(chars.indexOf(c)+1);
			}
		}
		
		/* compiling pattern and checking input string */
		Pattern pattern = Pattern.compile(patternBuffer.toString());
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return 1;
		}
		
		return 0;
	}

}
