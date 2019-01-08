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
				if (currentLevel.nextRoundCanBeExecuted(doctor)) {
					switch (event.getCode()) {
					case W:
						if (doctor.getCoordinates().getY() < drawer.size - 1) {
							currentLevel.play(doctor, "u");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case X:
						if (doctor.getCoordinates().getY() > 0) {
							currentLevel.play(doctor, "d");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case A:
						if (doctor.getCoordinates().getX() > 0) {
							currentLevel.play(doctor, "l");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case D:
						if (doctor.getCoordinates().getX() < drawer.size - 1) {
							currentLevel.play(doctor, "r");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case Q:
						if ((doctor.getCoordinates().getY() < drawer.size - 1) && (doctor.getCoordinates().getX() > 0)) {
							currentLevel.play(doctor, "ul");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case E:
						if ((doctor.getCoordinates().getY() < drawer.size - 1) && (doctor.getCoordinates().getX() < drawer.size - 1)) {
							currentLevel.play(doctor, "ur");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case C:
						if ((doctor.getCoordinates().getY() > 0) && (doctor.getCoordinates().getX() < drawer.size - 1)) {
							currentLevel.play(doctor, "dr");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case Z:
						if ((doctor.getCoordinates().getY() > 0) && (doctor.getCoordinates().getX() > 0)) {
							currentLevel.play(doctor, "dl");
							LevelMap levelMapToDraw = currentLevel.getLevelMap();
							drawer.drawObjects(levelMapToDraw);
						}
						break;
					case T:
						currentLevel.play(doctor, "t");
						LevelMap levelMapToDraw = currentLevel.getLevelMap();
						drawer.drawObjects(levelMapToDraw);
						break;
					default:
						break;
					}
				}

				if (!doctor.hasBeenAttacked() && !currentLevel.nextRoundCanBeExecuted(doctor)) {
					gameScore += currentLevel.getLevelScore();
					currentLevelNumber++;
					drawer.updateLabels(gameScore, currentLevelNumber, doctor.getHealth());
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
