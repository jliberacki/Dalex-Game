package view;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.LevelMap;

public class Drawer {
	
	private GridPane root;
	private Stage primaryStage;
	public int currentX;
	public int currentY;
	public int size;
	public int windowSize;
	
	static final String squareStyle = "-fx-background-color: white; -fx-border-color: black;";
	
	//public Image img = new Image("http://android-boom.pl/uploads/posts/2016-04/1460641874_candycons-icon-pack_1.png");
	public Image doctor = new Image("images/doctor.png");
	public Image dalek = new Image("images/dalek.png");
	
	public Drawer(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void drawMap(int size) {
		GridPane root = new GridPane();
		this.root = root;
		this.size = size;
		this.windowSize = 400;
				
		drawBoard();
		
		Scene scene = createScene(size-1);
		
		//drawObject(0,0,dalek);
		//moveObject(0,0,0,1,dalek);
		
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public void drawObjects(LevelMap map) {
		map.getMap().entrySet().forEach(entry -> {
		    if(entry.getValue().hasDoctor()) {
		    	this.currentX = entry.getKey().getX();
		    	this.currentY = entry.getKey().getY();
		    	removeObject(currentX, currentY);
		    	drawObject(currentX, currentY, doctor);
		    } else if (entry.getValue().hasDalek()) {
		    	removeObject(entry.getKey().getX(), entry.getKey().getY());
		    	drawObject(entry.getKey().getX(), entry.getKey().getY(), dalek);
		    }
		}); 
	}
	
	public void drawBoard () {
		for (int row = 0; row < size; row++) {
	        for (int col = 0; col < size; col ++) {
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
	        this.root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
	        this.root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
	    }
	}
	
	public Scene createScene(int boardWidth) {
		
		Scene scene = new Scene(this.root, windowSize, windowSize);
	    
	    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	                case UP:    if(currentX>0) moveObject(currentX, currentY, currentX-1,currentY, doctor); break;
	                case DOWN:  if(currentX<boardWidth) moveObject(currentX, currentY, currentX+1,currentY, doctor); break;
	                case LEFT:  if(currentY>0) moveObject(currentX, currentY, currentX,currentY-1, doctor); break;
	                case RIGHT: if(currentY<boardWidth) moveObject(currentX, currentY, currentX,currentY+1, doctor); break;
	                default: break;
	            }
	        }
	    });
	    
	    return scene;
	}
	
	public void moveObject(int currentX, int currentY, int newX, int newY, Image img) {
		StackPane oldSquare = (StackPane) (this.root.getChildren().get(currentX*size+currentY));
		StackPane newSquare = (StackPane) (this.root.getChildren().get(newX*size+newY));
		ImageView oldImgView = (ImageView) oldSquare.getChildren().get(0);
		ImageView newImgView = (ImageView) newSquare.getChildren().get(0);
		
	    oldImgView.setImage(null);
	    newImgView.setImage(img);
	    
	    this.currentX = newX;
	    this.currentY = newY;
	}
	
	public void drawObject(int X, int Y, Image img) {
		StackPane Square = (StackPane) (this.root.getChildren().get(X*size+Y));
		ImageView ImgView = (ImageView) Square.getChildren().get(0);
		
	    ImgView.setImage(img);
	}
	
	public void removeObject(int X, int Y) {
		StackPane Square = (StackPane) (this.root.getChildren().get(X*size+Y));
		ImageView ImgView = (ImageView) Square.getChildren().get(0);
		
	    ImgView.setImage(null);
	}
}
