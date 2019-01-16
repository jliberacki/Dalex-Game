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
import model.Move;
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
		Move move = new Move(drawer, doctor);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (currentLevel.nextRoundCanBeExecuted(doctor)) {
					switch (event.getCode()) {
					case W:
						if (move.validateMove("u")) {
							move.executeMove(currentLevel, "u");
						}
						break;
					case X:
						if (move.validateMove("d")) {
							move.executeMove(currentLevel, "d");
						}
						break;
					case A:
						if (move.validateMove("l")) {
							move.executeMove(currentLevel, "l");
						}
						break;
					case D:
						if (move.validateMove("r")) {
							move.executeMove(currentLevel, "r");
						}
						break;
					case Q:
						if (move.validateMove("ul")) {
							move.executeMove(currentLevel, "ul");
						}
						break;
					case E:
						if (move.validateMove("ur")) {
							move.executeMove(currentLevel, "ur");
						}
						break;
					case C:
						if (move.validateMove("dr")) {
							move.executeMove(currentLevel, "dr");
						}
						break;
					case Z:
						if (move.validateMove("dl")) {
							move.executeMove(currentLevel, "dl");
						}
						break;
					case T:
						move.executeMove(currentLevel, "t");
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
