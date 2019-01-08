package view;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LevelMap;

public class Drawer {

	private GridPane root;
	private Stage primaryStage;
	private Scene scene;
	public int currentX;
	public int currentY;
	public int size;
	public int windowSize;

	static final String squareStyle = "-fx-background-color: white; -fx-border-color: black;";

	public Image doctor = new Image("images/doctor.png");
	public Image dalek = new Image("images/dalek.png");
	public Image junk = new Image("images/junk.png");
	public Image powerup = new Image("images/powerup.png");

	public Drawer(Stage primaryStage, GridPane root, Scene scene) {
		this.primaryStage = primaryStage;
		this.root = root;
		this.scene = scene;
	}

	/**
	 * 
	 * @param size
	 */
	public void drawMap(int size) {
		this.size = size;
		this.windowSize = 400;
		drawBoard();
		Text scoreLabel = new Text("Score: 0");
		Text numberOfLivesLabel = new Text("Lives: 1");
		Text levelNumberLabel = new Text("Level: 1");
		this.root.add(scoreLabel, 0, size);
		this.root.add(numberOfLivesLabel, 0, size + 1);
		this.root.add(levelNumberLabel, 0, size + 2);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}

	/**
	 * 
	 * @param newScore
	 */
	public void updateLabels(int newScore, int newLevel, int newLives) {
		Text scoreLabel = (Text) this.root.getChildren().get((size) * (size));
		scoreLabel.setText("Score: " + newScore);
		Text numberOfLivesLabel = (Text) this.root.getChildren().get((size) * (size) + 1);
		numberOfLivesLabel.setText("Lives: " + newLives);
		Text levelNumberLabel = (Text) this.root.getChildren().get((size) * (size) + 2);
		levelNumberLabel.setText("Level: " + newLevel);
	}

	/**
	 * 
	 * @param map
	 */
	public void drawObjects(LevelMap map) {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				removeObject(row, col);
			}
		}
		map.getMap().entrySet().forEach(entry -> {
			if (entry.getValue().hasDoctor()) {
				this.currentX = entry.getKey().getX();
				this.currentY = entry.getKey().getY();
				removeObject(currentX, currentY);
				drawObject(currentX, currentY, doctor);
			} else if (entry.getValue().hasDalek()) {
				removeObject(entry.getKey().getX(), entry.getKey().getY());
				drawObject(entry.getKey().getX(), entry.getKey().getY(), dalek);
			} else if (entry.getValue().hasJunk()) {
				removeObject(entry.getKey().getX(), entry.getKey().getY());
				drawObject(entry.getKey().getX(), entry.getKey().getY(), junk);
			} else if (entry.getValue().hasPowerUp()) {
				removeObject(entry.getKey().getX(), entry.getKey().getY());
				drawObject(entry.getKey().getX(), entry.getKey().getY(), powerup);
			}
		});
	}

	/**
	 * 
	 */
	public void drawBoard() {
		for (int row = size - 1; row >= 0; row--) {
			for (int col = 0; col < size; col++) {
				StackPane square = new StackPane();
				square.setStyle(squareStyle);
				ImageView imgView = new ImageView();
				imgView.setImage(null);
				imgView.setFitHeight(windowSize / size - 5);
				imgView.setFitWidth(windowSize / size - 5);
				square.getChildren().add(imgView);
				this.root.add(square, col, row);
			}
		}
		for (int i = 0; i < size; i++) {
			this.root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE,
					Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
			this.root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY,
					Priority.ALWAYS, VPos.CENTER, true));
		}

	}

	/**
	 * 
	 * @param currentX
	 * @param currentY
	 * @param newX
	 * @param newY
	 * @param img
	 */
	public void moveObject(int currentX, int currentY, int newX, int newY, Image img) {
		StackPane oldSquare = (StackPane) (this.root.getChildren().get(currentX * size + currentY));
		StackPane newSquare = (StackPane) (this.root.getChildren().get(newX * size + newY));
		ImageView oldImgView = (ImageView) oldSquare.getChildren().get(0);
		ImageView newImgView = (ImageView) newSquare.getChildren().get(0);

		oldImgView.setImage(null);
		newImgView.setImage(img);

		this.currentX = newX;
		this.currentY = newY;
	}

	/**
	 * 
	 * @param X
	 * @param Y
	 * @param img
	 */
	public void drawObject(int X, int Y, Image img) {
		StackPane Square = (StackPane) (this.root.getChildren().get(X + size * Y));
		ImageView ImgView = (ImageView) Square.getChildren().get(0);
		ImgView.setImage(img);
	}

	/**
	 * 
	 * @param X
	 * @param Y
	 */
	public void removeObject(int X, int Y) {
		StackPane Square = (StackPane) (this.root.getChildren().get(X + size * Y));
		ImageView ImgView = (ImageView) Square.getChildren().get(0);
		ImgView.setImage(null);
	}
}
