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
	public Game(int health) {
		this.score = 0;
		Game.doctor = new Doctor(health);
	}

	/**
	 * Generates next levels and ends game if player is out of lifes.
	 */
	public void gameLoop() {
		int currentLevelNumber = 1;
		while (Game.doctor.getHealth() > 0) {
			currentLevel = new Level(currentLevelNumber);

			/*
			 * Executes this loop until player is out of lifes or until he wins level.
			 */
			while (!currentLevel.play()) {
				Game.doctor.decreaseHealth();
				if (Game.doctor.getHealth() <= 0) {
					break;
				}
				currentLevel = new Level(currentLevelNumber);
			}
			currentLevelNumber++;
			this.score += currentLevel.getLevelScore();
		}
		endGame();
	}

	/**
	 * Ends game, shows results.
	 */
	private void endGame() {
		System.out.println("Koniec gry lalalala");
		// TODO
	}
}
