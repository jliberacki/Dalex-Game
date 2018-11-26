package model;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author kuba
 *
 */
public class Level {

	private int levelNumber;
	private int levelScore;
	private RoundHandler currentRound;
	private List<RoundHandler> roundHistory;
	private int sizeOfMap = 10;

	/**
	 * Initialazes level.
	 * 
	 * @param levelNumber
	 */
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
		this.levelScore = 0;
		roundHistory = new LinkedList<>();
		currentRound = new RoundHandler(sizeOfMap, generateDaleksNumber());
	}

	/**
	 * Generate daleks number based on levelNumber.
	 * 
	 * @return
	 */
	public int generateDaleksNumber() {
		return levelNumber * 2;
	}

	/**
	 * Loop for every level. Generates new round until the level is over (doctor or
	 * all daleks are dead). It also saves Round to RoundHistory. Returns false if
	 * doctor is dead and true if all daleks are dead.
	 * 
	 * @return
	 */
	public boolean play() {
		while (true) {
			if (!Game.doctor.isAlive()) {
				return false;
			}
			if (currentRound.areDaleksDead()) {
				return true;
			}
			addRoundToHistory();
			currentRound.executeRound();
		}
	}

	/**
	 * Adds current round to history.
	 */
	private void addRoundToHistory() {
		roundHistory.add(currentRound.roundSnapshot());
	}

	/**
	 * Returns a score of level.
	 * 
	 * @return
	 */
	public int getLevelScore() {
		return levelScore;
	}

}
