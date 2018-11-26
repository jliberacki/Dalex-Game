package model.gameobjects;

/**
 * Increases number of lifes in the game
 * 
 * @author kuba
 *
 */
public class LifePowerUp extends PowerUp {

	@Override
	public void powerUp(Doctor doctor) {
		doctor.increaseHealth(1);
	}

}
