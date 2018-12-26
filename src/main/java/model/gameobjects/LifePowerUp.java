package model.gameobjects;

import model.Coordinates;

/**
 * Increases number of doctors lifes.
 * 
 * @author kuba
 *
 */
public class LifePowerUp extends PowerUp {

	@Override
	public void powerUp(Doctor doctor) {
		doctor.increaseHealth(1);
	}

	public LifePowerUp(Coordinates coordinates) {
		super.coordinates = coordinates;
	}

}
