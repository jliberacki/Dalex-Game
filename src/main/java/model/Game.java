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
	public static LevelMap currentLevelMap;
	/**
	 * Sets score to 0.
	 * 
	 * @param health
	 */
	public Game(Presenter presenter) {
		this.presenter=presenter;
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
		if((Game.doctor.isAlive())){
				currentLevel.play();
				if (Game.doctor.hasBeenAttacked() && Game.doctor.isAlive()) {
					currentLevel = new Level(currentLevelNumber);
					Game.doctor.setAttacked(false);
					currentLevel.play();
				}
				this.score += currentLevel.getLevelScore();
				currentLevelNumber++;
		}
		endGame();
	}

	public static void roundEnded(LevelMap levelMap) {
		currentLevelMap=levelMap;
		presenter.onRoundUpdate();
	}

	/**
	 * Ends game, shows results.
	 */
	private void endGame() {
		System.out.println("Koniec gry " + this.score);
	}
}
