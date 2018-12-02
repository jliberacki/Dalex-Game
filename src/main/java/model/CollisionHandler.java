package model;

/**
 * Chooses strategy to solve conflicts during collision.
 * 
 * @author kuba
 *
 */
public class CollisionHandler {

	/**
	 * Iterates through map, if collision is deteceted on any {@link Field} it is
	 * solved by solveCollision method.
	 * 
	 * @param levelMap
	 * @return
	 */
	public void handleCollisions(LevelMap levelMap) {
		for (Field field : levelMap.getMap().values()) {
			if (field.doesCollisionHappen()) {
				solveCollision(field);
			}
		}
	}

	/**
	 * Takes as an argument the {@link Field} where collision happened and solves
	 * it.
	 * 
	 * @param field
	 */
	private void solveCollision(Field field) {
		if (field.hasDoctor()) {
			solveCollisionWithDoctor(field);
		} else {
			solveCollisionWithoutDoctor(field);
		}
	}

	/**
	 * solves collision if there is doctor on this {@link Field}.
	 * 
	 * @param field
	 */
	private void solveCollisionWithDoctor(Field field) {
		if (field.hasPowerUp()) {
			field.getPowerUp().powerUpDoctor();
		}
		if (field.numberOfDaleks() > 0) {
			Game.doctor.decreaseHealth();
		}
	}

	/**
	 * Solves collision if there isn't doctor on this {@link Field}.
	 * 
	 * @param field
	 */
	private void solveCollisionWithoutDoctor(Field field) {
		if (field.numberOfDaleks() > 0) {
			if (field.hasPowerUp())
				field.removePowerUp();
			if (field.numberOfDaleks() > 1) {
				field.removeAllDaleks();
				field.setJunk();
			}
		}
	}
}
