package presenter;

import model.Level;
import model.LevelMap;
import model.gameobjects.Doctor;
import view.View;

public class Presenter {
	private View view;
	private int gameScore;
	private Level currentLevel;
	private Doctor doctor;
	private int currentLevelNumber;

	/**
	 * Main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Presenter presenter = new Presenter();
		presenter.startGame();
		while (presenter.continueGame()) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Sets score to 0.
	 * 
	 * @param health
	 */
	public Presenter() {
		this.gameScore = 0;
		this.doctor = new Doctor(1);
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
		while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
			currentLevel.play(this.doctor);
			levelMapToDraw = currentLevel.getLevelMap();
			// TU FUNKCJA RYSUJĄCA WIDOK NA PODSTAWIE MAPY PODCZAS GRY (PRZEMIESZCZAJĄCA
			// DOKTORA I DALEKI PO PLANSZY)
		}
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
				LevelMap levelMapToDraw = currentLevel.getLevelMap();
				// TU FUNKCJA RYSUJĄCA WIDOK NA PODSTAWIE MAPY PODCZAS GRY (PRZEMIESZCZAJĄCA
				// DOKTORA I DALEKI PO PLANSZY) - IDENTYCZNA JAK TA WYŻEJ
			}
			System.out.println("Level Score: " + currentLevel.getLevelScore());
			if (this.doctor.hasBeenAttacked() && this.doctor.isAlive()) {
				System.out.println("you lost one life");
				currentLevel = new Level(currentLevelNumber, doctor);
				this.doctor.setAttacked(false);
				while (currentLevel.nextRoundCanBeExecuted(this.doctor)) {
					currentLevel.play(this.doctor);
					LevelMap levelMapToDraw = currentLevel.getLevelMap();
					// TU FUNKCJA RYSUJĄCA WIDOK NA PODSTAWIE MAPY PODCZAS GRY (PRZEMIESZCZAJĄCA
					// DOKTORA I DALEKI PO PLANSZY) - IDENTYCZNA JAK TA WYŻEJ
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
