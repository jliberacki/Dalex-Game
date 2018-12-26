package model;

import model.gameobjects.Doctor;

/**
 * General class which have global score game.
 * 
 * @author kuba
 *
 */
public class Game {
	private int score;
	private Level currentLevel;
	private Doctor doctor;
	private LevelMap currentLevelMap;
	private int currentLevelNumber;

	/**
	 * Sets score to 0.
	 * 
	 * @param health
	 */
	public Game() {
		this.score = 0;
		this.doctor = new Doctor(1);
	}

	public void startGame() {
		int currentLevelNumber = 1;
		currentLevel = new Level(currentLevelNumber, doctor);
		currentLevel.play();
	}

	/**
	 * Generates next {@link Level}s and ends game if player is out of lifes.
	 */
	public void continueGame() {
		if (this.doctor.isAlive()) {
			currentLevel.play();
			if (this.doctor.hasBeenAttacked() && this.doctor.isAlive()) {
				System.out.println("you lost one life");
				currentLevel = new Level(currentLevelNumber, doctor);
				this.doctor.setAttacked(false);
				currentLevel.play();
			}
			this.score += currentLevel.getLevelScore();
			currentLevelNumber++;
		} else {
			endGame();
		}
	}

	/**
	 * Ends game, shows results.
	 */
	private void endGame() {
		System.out.println("Koniec gry " + this.score);
	}

}
