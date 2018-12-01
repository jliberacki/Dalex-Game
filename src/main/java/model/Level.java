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
	private MapInitializer mapInitializer;

	/**
	 * Initialazes level.
	 * 
	 * @param levelNumber
	 */
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
		this.levelScore = 0;
		roundHistory = new LinkedList<>();
		this.mapInitializer = new MapInitializer();
		currentRound = new RoundHandler(sizeOfMap, mapInitializer.initializeMap(this.levelNumber, sizeOfMap));
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
	public void play() {
		while (currentRound.nextRoundCanBeExecuted()) {
			addRoundToHistory();
			currentRound.executeRound();
			this.levelScore += currentRound.getAndClearRoundScore();
		}
	}

	/**
	 * Adds current round to history.
	 */
	private void addRoundToHistory() {
		roundHistory.add(currentRound.roundSnapshot());
	}

	private void clearHistory() {
		roundHistory.clear();
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
