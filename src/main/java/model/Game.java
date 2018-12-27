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

	/**
	 * Use this function to start the game.
	 */
	public void startGame() {
		int currentLevelNumber = 1;
		currentLevel = new Level(currentLevelNumber, doctor);
		currentLevel.play();
	}

	/**
	 * Generates next {@link Level}s and ends game if player is out of lifes. if
	 * returns game can be continiued, otherwise returns false.
	 */
	public boolean continueGame() {
		if (this.doctor.isAlive()) {
			System.out.println("CONGRATULATIONS! NEXT LEVEL");
			currentLevelNumber++;
			currentLevel = new Level(currentLevelNumber, doctor);
			currentLevel.play();
			if (this.doctor.hasBeenAttacked() && this.doctor.isAlive()) {
				System.out.println("you lost one life");
				currentLevel = new Level(currentLevelNumber, doctor);
				this.doctor.setAttacked(false);
				currentLevel.play();
			}
			this.score += currentLevel.getLevelScore();
			return true;
		} else {
			endGame();
			return false;
		}
	}

	/**
	 * Ends game, shows results.
	 */
	private void endGame() {
		System.out.println("Koniec gry " + this.score);
	}

}
