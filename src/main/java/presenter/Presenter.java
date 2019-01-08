package presenter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Level;
import model.LevelMap;
import model.gameobjects.Doctor;
import view.Drawer;

public class Presenter extends Application {
	private int gameScore;
	private Level currentLevel;
	private Doctor doctor;
	private int currentLevelNumber;
	private boolean canGameBeContinued;

	private void startGame(Drawer drawer) {
		this.gameScore = 0;
		this.doctor = new Doctor(1);
		this.currentLevelNumber = 1;
		this.currentLevel = new Level(currentLevelNumber, this.doctor);
		LevelMap levelMapToDraw = this.currentLevel.getLevelMap();
		drawer.drawMap(levelMapToDraw.getSize());
		drawer.drawObjects(levelMapToDraw);
		canGameBeContinued = true;

	}

	public void continueGame(String newMove, Drawer drawer) {
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
			canGameBeContinued = true;
		} else {
			endGame();
			canGameBeContinued = false;
		}
	}

	private void endGame() {
		System.out.println("Koniec gry " + this.gameScore);
	}

	@Override
	public void start(Stage primaryStage) {

		GridPane root = new GridPane();
		Scene scene = new Scene(root, 400, 400);
		Drawer drawer = new Drawer(primaryStage, root, scene);
		startGame(drawer);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("LEVEL" + currentLevelNumber);
				if (currentLevel.nextRoundCanBeExecuted(doctor)) {
					switch (event.getCode()) {
					case W:
						if (drawer.currentY < drawer.size - 1) {
							System.out.println("W");
							currentLevel.play(doctor, "u");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case X:
						if (drawer.currentY > 0) {
							System.out.println("X");
							currentLevel.play(doctor, "d");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case A:
						if (drawer.currentX > 0) {
							System.out.println("A");
							currentLevel.play(doctor, "l");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case D:
						if (drawer.currentX < drawer.size - 1) {
							System.out.println("D");
							currentLevel.play(doctor, "r");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case Q:
						if ((drawer.currentY < drawer.size - 1) && (drawer.currentX > 0)) {
							System.out.println("Q");
							currentLevel.play(doctor, "ul");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case E:
						if ((drawer.currentY < drawer.size - 1) && (drawer.currentX < drawer.size - 1)) {
							System.out.println("E");
							currentLevel.play(doctor, "ur");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case C:
						if ((drawer.currentY > 0) && (drawer.currentX < drawer.size - 1)) {
							System.out.println("C");
							currentLevel.play(doctor, "dr");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case Z:
						if ((drawer.currentY > 0) && (drawer.currentX > 0)) {
							System.out.println("Z");
							currentLevel.play(doctor, "dl");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case T:
						System.out.println("T");
						currentLevel.play(doctor, "t");
						LevelMap levelMapToDraw = currentLevel.getLevelMap();
						drawer.drawObjects(levelMapToDraw);
						break;
					default:
						break;
					}
				}

				if (!doctor.hasBeenAttacked() && !currentLevel.nextRoundCanBeExecuted(doctor)) {
					currentLevelNumber++;
					currentLevel = new Level(currentLevelNumber, doctor);
					LevelMap levelMapToDraw = currentLevel.getLevelMap();
					drawer.drawObjects(levelMapToDraw);
				}
				if (doctor.hasBeenAttacked() && !currentLevel.nextRoundCanBeExecuted(doctor)) {
					currentLevel = new Level(currentLevelNumber, doctor);
					LevelMap levelMapToDraw = currentLevel.getLevelMap();
					drawer.drawObjects(levelMapToDraw);
				}

				if (!doctor.isAlive()) {
					endGame();
					Platform.exit();
				}
			}
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}
