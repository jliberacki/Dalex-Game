package presenter;

import javafx.stage.Stage;
import model.Level;
import model.LevelMap;
import model.gameobjects.Doctor;
import view.Drawer;

public class Presenter {
	private Drawer drawer;
	private int gameScore;
	private Level currentLevel;
	private Doctor doctor;
	private int currentLevelNumber;
	private boolean gameCanBeContinued;

	/**
	 * Main function
	 * 
	 * @param args
	 */

	/**
	 * Sets score to 0.
	 * 
	 * @param health
	 */
	public Presenter(Drawer drawer) {
		this.gameScore = 0;
		this.doctor = new Doctor(1);
		this.drawer = drawer;
	}

	public Level getCurrentLevel() {
		return this.currentLevel;
	}

	/**
	 * Use this function to start the game.
	 */
	public void startGame() {
		int currentLevelNumber = 1;
		this.currentLevel = new Level(currentLevelNumber, this.doctor);
		LevelMap levelMapToDraw = this.currentLevel.getLevelMap();
		drawer.drawMap(levelMapToDraw.getSize());
		drawer.drawObjects(levelMapToDraw);
		gameCanBeContinued = true;
		// while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
		// currentLevel.play(this.doctor);
		// levelMapToDraw = currentLevel.getLevelMap();
		// drawer.drawObjects(levelMapToDraw);
		// }
	}

	/**
	 * Generates next {@link Level}s and ends game if player is out of lives. if
	 * returns game can be continued, otherwise returns false.
	 */
	public void continueGame(String newMove) {
		if (this.doctor.isAlive()) {
			// System.out.println("CONGRATULATIONS! NEXT LEVEL");
			currentLevelNumber++;
			currentLevel = new Level(currentLevelNumber, this.doctor);
			while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
				currentLevel.play(this.doctor, newMove);
				LevelMap levelMapToDraw = this.currentLevel.getLevelMap();
				drawer.drawObjects(levelMapToDraw);
			}
			// System.out.println("Level Score: " +
			// currentLevel.getLevelScore());
			if (this.doctor.hasBeenAttacked() && this.doctor.isAlive()) {
				// System.out.println("you lost one life");
				currentLevel = new Level(currentLevelNumber, doctor);
				this.doctor.setAttacked(false);
				while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
					currentLevel.play(this.doctor, newMove);
					LevelMap levelMapToDraw = this.currentLevel.getLevelMap();
					drawer.drawObjects(levelMapToDraw);
				}
			}
			this.gameScore += currentLevel.getLevelScore();
			// System.out.println("Game score: " + gameScore);
			gameCanBeContinued = true;
		} else {
			endGame();
			gameCanBeContinued = false;
		}
	}

	/**
	 * Returns true if game can be continued.
	 * 
	 * @return
	 */
	public boolean canGameBeContinued() {
		return gameCanBeContinued;
	}

	/**
	 * Ends game, shows results.
	 */
	private void endGame() {
		System.out.println("Koniec gry " + this.gameScore);
	}
}
