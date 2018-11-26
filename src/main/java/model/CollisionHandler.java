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
			if (field.powerUp != null) {
				field.powerUp.powerUp(Game.doctor);
			}
			else {
				
			}
		} else {
			field.removePowerUp();
			while (field.daleks.size() > 1) {
				CollisionResult collisionResult = field.daleks.get(0).attack(field.daleks.get(1));
			}
		}
	}
}
