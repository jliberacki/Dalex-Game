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
	 * Iterates through map, if collision is deteceted on any field it is solved by solveCollision metho.
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

	private void solveCollision(Field field) {

	}
}
