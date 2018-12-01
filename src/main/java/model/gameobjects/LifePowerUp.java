package model.gameobjects;

import model.Coordinates;
import model.Game;

/**
 * Increases number of lifes in the game
 * 
 * @author kuba
 *
 */
public class LifePowerUp extends PowerUp {

	@Override
	public void powerUpDoctor() {
		Game.doctor.increaseHealth(1);
	}

	public LifePowerUp(Coordinates coordinates) {
		super.coordinates = coordinates;
	}

}
