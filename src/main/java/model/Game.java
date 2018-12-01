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
	public static Doctor doctor;

	/**
	 * Sets score to 0.
	 * 
	 * @param health
	 */
	public Game() {
		this.score = 0;
		Game.doctor = new Doctor(1);
	}

	/**
	 * Generates next levels and ends game if player is out of lifes.
	 */
	public void gameLoop() {
		int currentLevelNumber = 1;
		while (Game.doctor.isAlive()) {
			currentLevel = new Level(currentLevelNumber);
			currentLevel.play();
			while (Game.doctor.hasBeenAttacked() && Game.doctor.isAlive()) {
				currentLevel = new Level(currentLevelNumber);
				currentLevel.play();
			}
			this.score += currentLevel.getLevelScore();
		}
		endGame();
	}

	/**
	 * Ends game, shows results.
	 */
	private void endGame() {
		System.out.println("Koniec gry " + this.score);
	}
}
