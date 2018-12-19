package model.gameobjects;

import model.Coordinates;

/**
 * Junk is a GameObject which is created during daleks collision.
 * 
 * @author kuba
 *
 */
public class Junk extends GameObject implements ImmovableObject {

	public Junk(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
