package model.gameobjects;

import model.Game;

/**
 * Increases number of lifes in the game
 * 
 * @author kuba
 *
 */
public class LifePowerUp extends PowerUp {

	@Override
	public void powerUp() {
		Game.doctor.increaseHealth(1);
	}

}
