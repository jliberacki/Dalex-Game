package model;

import java.util.LinkedList;
import java.util.List;

import model.gameobjects.Dalek;
import model.gameobjects.GameObject;

/**
 * Represtns one level of the game. Number of {@link Dalek}s on map and other {@link GameObject}s,
 * size of map depends on him. Create new level to run rounds of game.
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
	private LevelMapFactory levelMapFactory;

	/**
	 * Initialazes {@link Level}.
	 * 
	 * @param levelNumber
	 */
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
		this.levelScore = 0;
		roundHistory = new LinkedList<>();
		this.levelMapFactory = new LevelMapFactory();
		currentRound = new RoundHandler(levelMapFactory.initializeMap(this.levelNumber, sizeOfMap));
	}

	/**
	 * Generate {@link Dalek}s number based on levelNumber.
	 * 
	 * @return
	 */
	public int generateDaleksNumber() {
		return levelNumber * 2;
	}

	/**
	 * Loop for every {@link Level}. Generates new round until the {@link Level} is over (doctor or
	 * all daleks are dead). It also saves Round to {@link RoundHistory}. Returns false if
	 * doctor is dead and true if all {@link Dalek}s are dead.
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

	/**
	 * Returns a score of {@link Level}.
	 * 
	 * @return
	 */
	public int getLevelScore() {
		return levelScore;
	}

}
