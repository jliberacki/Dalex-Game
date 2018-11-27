package model;

import java.util.Map;

/**
 * Chooses strategy to solve conflicts during collision.
 * 
 * @author kuba
 *
 */
public class CollisionHandler {

	/**
	 * Iterates through map, if collision is deteceted on any field it is solved by
	 * solveCollision metho.
	 * 
	 * @param map
	 * @return
	 */
	public Map<Coordinates, Field> handleCollisions(Map<Coordinates, Field> map) {
		for (Map.Entry<Coordinates, Field> entry : map.entrySet()) {
			if (entry.getValue().doesCollisionHappen()) {
				solveCollision(entry.getValue());
			}
		}
		return map;
	}

	/**
	 * Takes as an argument the field where collision happened and solves it.
	 * 
	 * @param field
	 */
	private void solveCollision(Field field) {
		// if doctor is on this field
		if (Game.doctor.getCoordinates().equals(field.getCoordinates())) {
			if (field.hasPowerUp()) {
				field.getPowerUp().powerUp(Game.doctor);
				field.removePowerUp();
			}
			while (field.getDaleks().size() > 0) {
				field.applyCollisionResult(field.getDaleks().get(0).attack(Game.doctor));
			}
		// if there is no doctor on this field
		} else {
			field.removePowerUp();
			while (field.getDaleks().size() > 1) {
				field.applyCollisionResult(field.getDaleks().get(0).attack(field.getDaleks().get(1)));
			}
		}
	}
}
