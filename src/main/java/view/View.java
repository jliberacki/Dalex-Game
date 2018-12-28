package view;

import javafx.application.Application;
import javafx.stage.Stage;
import presenter.Presenter;



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
