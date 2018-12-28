package presenter;

import javafx.stage.Stage;
import model.Level;
import model.gameobjects.Doctor;
import view.Drawer;
import view.View;

public class Presenter {
	private Drawer drawer;
	private int gameScore;
	private Level currentLevel;
	private Doctor doctor;
	private int currentLevelNumber;

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
	public Presenter(Stage PrimaryStage) {
		this.gameScore = 0;
		this.doctor = new Doctor(1);
		this.drawer = new Drawer(PrimaryStage);
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
		this.currentLevel.getLevelMap();
		// TU FUNKCJA RYSUJĄCA WIDOK NA PODSTAWIE MAPY NA POCZATEK GRY, CURRENT MAP TO
		// MAPA DO NARYSOWANIA
		drawer.drawMap(this.currentLevel.getLevelMap());
//		while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
//			currentLevel.play(this.doctor);
//		}
	}

	/**
	 * Generates next {@link Level}s and ends game if player is out of lifes. if
	 * returns game can be continiued, otherwise returns false.
	 */
	public boolean continueGame() {
		if (this.doctor.isAlive()) {
			System.out.println("CONGRATULATIONS! NEXT LEVEL");
			currentLevelNumber++;
			currentLevel = new Level(currentLevelNumber, this.doctor);
			while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
				currentLevel.play(this.doctor);
			}
			System.out.println("Level Score: " + currentLevel.getLevelScore());
			if (this.doctor.hasBeenAttacked() && this.doctor.isAlive()) {
				System.out.println("you lost one life");
				currentLevel = new Level(currentLevelNumber, doctor);
				this.doctor.setAttacked(false);
				while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
					currentLevel.play(this.doctor);
				}
			}
			this.gameScore += currentLevel.getLevelScore();
			System.out.println("Game score: " + gameScore);
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
		System.out.println("Koniec gry " + this.gameScore);
	}
}
