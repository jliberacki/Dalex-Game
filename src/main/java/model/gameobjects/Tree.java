package model.gameobjects;

import model.Coordinates;

/**
 * Immovable GameObject with whom other objects can't collide.
 * 
 * @author kuba
 *
 */
public class Tree extends GameObject implements ImmovableObject {

	public Tree(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
