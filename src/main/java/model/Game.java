package model;

import java.io.IOException;

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
	public static LevelMap currentLevelMap;
	private int currentLevelNumber;

	/**
	 * Sets score to 0.
	 * 
	 * @param health
	 */
	public Game() {
		this.score = 0;
		Game.doctor = new Doctor(1);
	}

	public void startGame() {
		int currentLevelNumber = 1;
		currentLevel = new Level(currentLevelNumber);
		currentLevel.play();
	}

	/**
	 * Generates next {@link Level}s and ends game if player is out of lifes.
	 */
	public void continueGame() {
		if (Game.doctor.isAlive()) {
			currentLevel.play();
			while (Game.doctor.hasBeenAttacked() && Game.doctor.isAlive()) {
				System.out.println("in while!!");
				currentLevel = new Level(currentLevelNumber);
				Game.doctor.setAttacked(false);
				currentLevel.play();
			}
			this.score += currentLevel.getLevelScore();
			currentLevelNumber++;
		} else {
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
