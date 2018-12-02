package model.gameobjects;

import model.Coordinates;

/**
 * Immovable GameObject with whom other objects can't collide.
 * 
 * @author kuba
 *
 */
public class Stone extends GameObject implements ImmovableObject {

	public Stone(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
