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
		Text scoreLabel = new Text("   Score: 0");
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
		scoreLabel.setText("   Score: " + newScore);
		Text numberOfLivesLabel = (Text) this.root.getChildren().get((size) * (size) + 1);
		numberOfLivesLabel.setText("Lives: " + newLives);
		Text levelNumberLabel = (Text) this.root.getChildren().get((size) * (size) + 2);
		levelNumberLabel.setText("Level: " + newLevel);
	}
	
	public void clearMap() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				removeObject(row, col);
			}
		}
	}

	/**
	 * 
	 * @param map
	 */
	public void drawObjects(LevelMap map) {
		clearMap();
		map.getMap().entrySet().forEach(entry -> {
			int x = entry.getKey().getX();
			int y = entry.getKey().getY();
			if (entry.getValue().hasDoctor()) {
				removeObject(x, y);
				drawObject(x, y, doctor);
			} else if (entry.getValue().hasDalek()) {
				removeObject(x, y);
				drawObject(x, y, dalek);
			} else if (entry.getValue().hasJunk()) {
				removeObject(x, y);
				drawObject(x, y, junk);
			} else if (entry.getValue().hasPowerUp()) {
				removeObject(x, y);
				drawObject(x, y, powerup);
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
			this.root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_PREF_SIZE,
					Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
			this.root.getRowConstraints().add(new RowConstraints(5, Control.USE_PREF_SIZE, Double.POSITIVE_INFINITY,
					Priority.ALWAYS, VPos.CENTER, true));
		}

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
