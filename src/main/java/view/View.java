package view;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import presenter.Presenter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class View extends Application {
	private Presenter presenter;
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		GridPane root = new GridPane();
		Scene scene = new Scene(root, 400, 400);
		Drawer drawer = new Drawer(primaryStage, root, scene);
		this.presenter = new Presenter(drawer);

		presenter.startGame();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("lalalala2");
				if (presenter.canGameBeContinued()) {
					System.out.println("lalalala");
					switch (event.getCode()) {
					case UP:
						if (drawer.currentX > 0) {
							drawer.moveObject(drawer.currentX, drawer.currentY, drawer.currentX - 1, drawer.currentY,
									drawer.doctor);
							presenter.continueGame("u");
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
			}
		});

		// while (presenter.continueGame()) {
		// try {
		// Thread.currentThread().sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
