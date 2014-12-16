/**
 * 
 * Booking.com interview task
 * 
 * Given a list/array of names(String) sort them such that each name is followed
 * by a name which starts with the last character of the previous name. 
 * 
 * input:  Luis Hector Selena Emmanuel Amish
 * 
 * output: Emmanuel Luis Selena Amish Hector
 * 
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class NameOrder {

	public static void main(String[] args) {
		String[] arranged = arrangeNames(new String[] { "Luis", "Romeo",
				"Hector", "Selena", "Emmanuel", "Amish" });

		for (String s : arranged) {
			System.out.println(s);
		}
	}

	private static String[] arrangeNames(String[] names) {
		String[] out = new String[names.length];

		for (int k = 0; k < names.length; k++) {
			/* find next corresponding name */
			out[k] = findNext(k, k > 1 ? out[k - 2] : null,
					k > 0 ? out[k - 1].substring(out[k - 1].length() - 1)
							: null, names);
		}

		return out;
	}

	private static String findNext(int count, String string, String lChar,
			String[] names) {

		/* first of all, let's find start name */
		if (count == 0) {
			for (int k = 0; k < names.length; k++) {
				String fChar = names[k].substring(0, 1);
				/* looking for an appropriate name to start the chain */
				boolean found = true;
				for (int q = 0; q < names.length; q++) {
					String lastChar = names[q].substring(names[q].length() - 1);
					if (q != k && lastChar.equalsIgnoreCase(fChar)) {
						/* this name can be an intermediate part of the chain, continue */
						found = false;
						break;
					}
				}

				if (found) {
					return names[k];
				}
			}
		}

		/* looking for the name to continue chain */
		for (int q = 0; q < names.length; q++) {
			String fChar = names[q].substring(0, 1);
			if (fChar.equalsIgnoreCase(lChar)
					&& !names[q].equalsIgnoreCase(string)) {
				return names[q];
			}
		}

		return null;
	}

}
