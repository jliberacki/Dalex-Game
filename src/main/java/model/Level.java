package model;

import model.gameobjects.Dalek;
import model.gameobjects.Doctor;
import model.gameobjects.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Represtns one level of the game. Number of {@link Dalek}s on map and other
 * {@link GameObject}s, size of map depends on him. Create new level to run
 * rounds of game.
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
	private LevelMap levelMap;

	/**
	 * Initialazes {@link Level}.
	 * 
	 * @param levelNumber
	 */
	public Level(int levelNumber, Doctor doctor) {
		this.levelNumber = levelNumber;
		this.levelScore = 0;
		roundHistory = new LinkedList<>();
		this.levelMapFactory = new LevelMapFactory();
		this.levelMap = levelMapFactory.initializeMap(this.levelNumber, getSizeOfMap(), doctor);
		currentRound = new RoundHandler(this.levelMap, doctor);
		doctor.setAttacked(false);
	}

	/**
	 * returns levelMap
	 * 
	 * @return
	 */
	public LevelMap getLevelMap() {
		return levelMap;
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
	 * Loop for every {@link Level}. Generates new round until the {@link Level} is
	 * over (doctor or all daleks are dead). It also saves Round to
	 * {@link RoundHistory}. Returns false if doctor is dead and true if all
	 * {@link Dalek}s are dead.
	 * 
	 * @return
	 */
	public LevelMap play(Doctor doctor, String newMove) {
		addRoundToHistory();
		LevelMap levelMap = currentRound.executeRound(doctor, newMove);
		this.levelScore += currentRound.getAndClearRoundScore();
		return levelMap;
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

	/**
	 * Returns true if next round can be executed, otherwise false
	 * 
	 * @return
	 */
	public boolean nextRoundCanBeExecuted(Doctor doctor) {
		return this.levelMap.isMoreThanZeroDaleksAlive() && !doctor.hasBeenAttacked();
	}

	public int getSizeOfMap() {
		return sizeOfMap;
	}

	public void setSizeOfMap(int sizeOfMap) {
		this.sizeOfMap = sizeOfMap;
	}

}
