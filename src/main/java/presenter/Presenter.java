package presenter;

import javafx.application.Application;
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

				switch (event.getCode()) {
				case UP:
					if (drawer.currentX > 0) {
						drawer.moveObject(drawer.currentX, drawer.currentY, drawer.currentX - 1, drawer.currentY,
								drawer.doctor);
						break;		
						
						
					}
				case DOWN:
					if (drawer.currentX < drawer.size - 1) {
						drawer.moveObject(drawer.currentX, drawer.currentY, drawer.currentX + 1, drawer.currentY,
								drawer.doctor);
						break;
					}
				case LEFT:
					if (drawer.currentY > 0) {
						drawer.moveObject(drawer.currentX, drawer.currentY, drawer.currentX, drawer.currentY - 1,
								drawer.doctor);
						break;
					}
				case RIGHT:
					if (drawer.currentY < drawer.size - 1) {
						drawer.moveObject(drawer.currentX, drawer.currentY, drawer.currentX, drawer.currentY + 1,
								drawer.doctor);
						break;
					}
				default:
					break;
				}

			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
