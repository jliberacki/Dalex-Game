package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;



public class View extends Application {
	
	private GridPane root;
	public int currentX;
	public int currentY;
	
	//public Image img = new Image("http://android-boom.pl/uploads/posts/2016-04/1460641874_candycons-icon-pack_1.png");
	public Image img = new Image("images/doctor.png");
	
    @Override
    public void start(Stage primaryStage) {
    	
        GridPane root = new GridPane();
        this.root = root;
        this.currentX = (int)(Math.random() * 8);
        this.currentY = (int)(Math.random() * 8);
        
        final int size = 8 ;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                StackPane square = new StackPane();
                square.setStyle("-fx-background-color: white; -fx-border-color: black;");
                ImageView imgView = new ImageView();
                if(row == this.currentX && col == this.currentY) imgView.setImage(this.img);
                else imgView.setImage(null);
                imgView.setFitHeight(45);
                imgView.setFitWidth(45);
                square.getChildren().add(imgView);
                this.root.add(square, col, row);
            }
        }
        for (int i = 0; i < size; i++) {
            this.root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            this.root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        
        Scene scene = new Scene(this.root, 400, 400);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    if(currentX>0) moveObject(currentX-1,currentY); break;
                    case DOWN:  if(currentX<7) moveObject(currentX+1,currentY); break;
                    case LEFT:  if(currentY>0) moveObject(currentX,currentY-1); break;
                    case RIGHT: if(currentY<7) moveObject(currentX,currentY+1); break;
                    default: break;
                }
            }
        });
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void moveObject(int newX, int newY) {
    	StackPane oldSquare = (StackPane) (this.root.getChildren().get(this.currentX*8+this.currentY));
    	StackPane newSquare = (StackPane) (this.root.getChildren().get(newX*8+newY));
    	ImageView oldImgView = (ImageView) oldSquare.getChildren().get(0);
    	ImageView newImgView = (ImageView) newSquare.getChildren().get(0);
    	
        oldImgView.setImage(null);
        newImgView.setImage(img);
        
        this.currentX = newX;
        this.currentY = newY;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
