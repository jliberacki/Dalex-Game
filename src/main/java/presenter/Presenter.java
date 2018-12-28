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
		LevelMap levelMapToDraw = this.currentLevel.getLevelMap();
		// TU FUNKCJA RYSUJĄCA WIDOK NA PODSTAWIE MAPY NA POCZATEK GRY - TAKA JAKBY
		// INICJALIZUJĄCA
		drawer.drawMap(levelMapToDraw.getSize());
		drawer.drawObjects(levelMapToDraw);
//		while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
//			currentLevel.play(this.doctor);
//			levelMapToDraw = currentLevel.getLevelMap();
//			// TU FUNKCJA RYSUJĄCA WIDOK NA PODSTAWIE MAPY PODCZAS GRY (PRZEMIESZCZAJĄCA
//			// DOKTORA I DALEKI PO PLANSZY)
//			drawer.drawObjects(levelMapToDraw);
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
				LevelMap levelMapToDraw = this.currentLevel.getLevelMap();
				// TU FUNKCJA RYSUJĄCA WIDOK NA PODSTAWIE MAPY PODCZAS GRY (PRZEMIESZCZAJĄCA
				// DOKTORA I DALEKI PO PLANSZY) - IDENTYCZNA JAK TA WYŻEJ
				drawer.drawObjects(levelMapToDraw);
			}
			System.out.println("Level Score: " + currentLevel.getLevelScore());
			if (this.doctor.hasBeenAttacked() && this.doctor.isAlive()) {
				System.out.println("you lost one life");
				currentLevel = new Level(currentLevelNumber, doctor);
				this.doctor.setAttacked(false);
				while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
					currentLevel.play(this.doctor);
					LevelMap levelMapToDraw = this.currentLevel.getLevelMap();
					// TU FUNKCJA RYSUJĄCA WIDOK NA PODSTAWIE MAPY PODCZAS GRY (PRZEMIESZCZAJĄCA
					// DOKTORA I DALEKI PO PLANSZY) - IDENTYCZNA JAK TA WYŻEJ
					drawer.drawObjects(levelMapToDraw);
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
