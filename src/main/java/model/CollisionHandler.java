package model;

import java.util.List;

import model.gameobjects.GameObject;

/**
 * Chooses strategy to solve conflicts during collision.
 * 
 * @author kuba
 *
 */
public class CollisionHandler {

	/**
	 * Takes list of collding objects that are on the same field and returns one
	 * object that will remain on this field.
	 * Example: [dalek, dalek] => junk
	 * It also takes care of powerUps.
	 * 
	 * @param collision
	 * @return
	 */
	public GameObject collisionResult(List<GameObject> collision) {
		return null;
	}
}
