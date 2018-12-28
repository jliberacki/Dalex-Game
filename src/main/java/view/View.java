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
import model.Coordinates;
import model.LevelMap;
import presenter.Presenter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;



public class View extends Application {

	private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
    	
    	this.primaryStage = primaryStage;
    	Presenter presenter = new Presenter(primaryStage);
    	presenter.startGame();
//    	while (presenter.continueGame()) {
//			try {
//				Thread.currentThread().sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
    	
    }
    
  
    public static void main(String[] args) {
        launch(args);
    }
}
