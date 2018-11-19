package model;

/**
 * General class which have global score game.
 * 
 * @author kuba
 *
 */
public class Game {
	private int score;
	private Level currentLevel;
	private int numberOfLifes;

	/**
	 * Sets score to 0.
	 * 
	 * @param numberOfLifes
	 */
	public Game(int numberOfLifes) {
		this.score = 0;
		this.numberOfLifes = numberOfLifes;
	}

	/**
	 * Generates next levels and ends game if player is out of lifes.
	 */
	public void gameLoop() {
		int currentLevelNumber = 1;
		while (numberOfLifes > 0) {
			currentLevel = new Level(currentLevelNumber);

			/*
			 * Executes this loop until player is out of lifes or until he wins level.
			 */
			while (!currentLevel.play()) {
				numberOfLifes -= 1;
				if (numberOfLifes <= 0) {
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
		// TODO
	}
}
