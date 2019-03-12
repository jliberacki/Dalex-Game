package model;

/**
 * Counts scores for collisions.
 * 
 * @author kuba
 *
 */
public class ScoreCounter {

	private final int POINTS_FOR_KILLING_ONE_DALEK = 10;
	private final int BONUS_FOR_KILLING_MORE_THAN_TWO_DALEKS = 5;
	private final int POINTS_FOR_PICKING_UP_POWER_UP = 7;

	/**
	 * If two or more daleks are in collision with each other this method will
	 * return score.
	 * 
	 * @param numberOfDaleks
	 * @return
	 */
	public int dalekVsDalek(int numberOfDaleks) {
		int result = numberOfDaleks * this.POINTS_FOR_KILLING_ONE_DALEK;
		if (numberOfDaleks > 2) {
			for (int i = 0; i < numberOfDaleks; i++)
				result += BONUS_FOR_KILLING_MORE_THAN_TWO_DALEKS * i * numberOfDaleks;
		}
		return result;
	}

	/**
	 * If one or more daleks are in collision with junk this method will return
	 * score.
	 * 
	 * @param numberOfDaleks
	 * @return
	 */
	public int dalekVsJunk(int numberOfDaleks) {
		int result = numberOfDaleks * this.POINTS_FOR_KILLING_ONE_DALEK;
		if (numberOfDaleks > 2) {
			for (int i = 0; i < numberOfDaleks; i++)
				result += BONUS_FOR_KILLING_MORE_THAN_TWO_DALEKS * numberOfDaleks;
		}
		return result;
	}

	/**
	 * Returns score for picking up power up.
	 * 
	 * @return
	 */
	public int powerUpPickUp() {
		return POINTS_FOR_PICKING_UP_POWER_UP;
	}
}
