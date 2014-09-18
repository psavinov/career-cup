/**
 * Facebook interview task
 * 
 * Given an array of positive integers that represents possible points a team
 * could score in an individual play. Now there are two teams play against each
 * other. Their final scores are S and S'. How would you compute the maximum
 * number of times the team that leads could have changed?
 * 
 * For example, if S=10 and S'=6. The lead could have changed 4 times: Team 1
 * scores 2, then Team 2 scores 3 (lead change); Team 1 scores 2 (lead change),
 * Team 2 score 0 (no lead change); Team 1 scores 0, Team 2 scores 3 (lead
 * change); Team 1 scores 3, Team 2 scores 0 (lead change); Team 1 scores 3,
 * Team 2 scores 0 (no lead change).
 * 
 * PS: bad example, real answer is 6 for the case
 * 
 * @author Pavel Savinov // savinovpa@gmail.com
 *
 */
public class TeamScore {

	public static void main(String[] args) {
		System.out.println(computeLeadChange(10, 6));
	}

	private static int computeLeadChange(int firstScore, int secondScore) {
		int leadChange = 0;

		int q = 0;
		boolean left = true;
		for (int k = 1; 
				k <= (firstScore >= secondScore ? 
						firstScore : secondScore); k++) {

			if (!left && k > q) {
				leadChange++;
				left = true;
			}

			while (left) {
				if (q <= (firstScore >= secondScore ? 
						secondScore : firstScore)) {
					q++;
				} else {
					break;
				}

				if (q > k) {
					left = false;
					leadChange++;
				}
			}

		}

		return leadChange;
	}

}
