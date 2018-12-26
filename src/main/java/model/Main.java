package model;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		game.startGame();
		while (true) {
			game.continueGame();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
