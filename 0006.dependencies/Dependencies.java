/**
 * Google In-Person interview task
 * 
 * You are given a text file that has list of dependencies between (any) two
 * projects in the soure code repository. Write an algorithm to determine the
 * build order ie. which project needs to be build first, followed by which
 * project..based on the dependencies.
 * 
 * Bonus point: If you can detect any circular dependencies and throw an
 * exception if found.
 * 
 * EX: ProjectDependencies.txt a -> b (means 'a' depends on 'b'..so 'b' needs to
 * be built first and then 'a')
 * 
 * a -> b b -> c b -> d c -> d
 * 
 * Then the build order can be
 * 
 * d, c, b, a in that order
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class Dependencies {

	public static void main(String[] args) {
		
		/* normal case */
		System.out.print("Normal case: ");
		System.out.println(buildOrder(
				new String[] { "a-b", "b-c", "b-d", "c-d"}));

		/* circular dependencies */
		System.out.println();
		System.out.println(buildOrder(
				new String[] { "a-b", "b-c", "c-a", "b-d", "c-d"}));

	}

	private static String buildOrder(String[] deps) {
		StringBuffer out = new StringBuffer("");

		int[] weights = new int[deps.length];
		int k = 0;
		int maxWeight = Integer.MIN_VALUE;
		for (String dep : deps) {
			weights[k++] = calcWeight(dep, deps, null);
			if (weights[k - 1] > maxWeight) {
				maxWeight = weights[k - 1];
			}
		}

		for (int w = 1; w <= maxWeight; w++) {
			for (int q = 0; q < deps.length; q++) {
				String[] parts = deps[q].split("-");
				if (weights[q] == w) {
					if (!out.toString().contains(parts[1].concat(","))) {
						out.append(parts[1]);
						if (w<maxWeight) {
							out.append(", ");
						}
					}
					if (!out.toString().contains(parts[0].concat(","))) {
						out.append(parts[0]);
						if (w<maxWeight) {
							out.append(", ");
						}
					}

				}
			}
		}

		return out.toString();
	}

	private static int calcWeight(String current, String[] deps, StringBuffer path) {
		int weight = 1;
		String[] pair = current.split("-");
		if (pair.length != 2) {
			throw new IllegalArgumentException(
					"Incorrect dependency: ".concat(current));
		}

		path = path == null ? new StringBuffer(current) : path;
		boolean circle = true;
		String lastEnd = "";
		for (String dep : deps) {
			String[] elements = dep.split("-");
			if (elements.length != 2) {
				throw new IllegalArgumentException(
						"Incorrect dependency: ".concat(dep));
			}

			if (!dep.equals(current) && !path.toString().contains(dep)) {
				if (elements[0].equals(pair[1]) || elements[0]
						.equals(pair[0])) {
					weight++;
					path.append("->").append(dep);
					circle = circle && lastEnd.equals(elements[0]);
					weight += calcWeight(dep, deps, path);
					lastEnd = elements[1];
				}
				
				if (path.charAt(0) == path.charAt(path.length() - 1) && circle) {
					throw new IllegalStateException(
							"Circular dependencies: ".concat(path.toString()));
				}
			}
		}

		return weight;
	}

}
