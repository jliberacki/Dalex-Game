package model.gameobjects;

import model.Coordinates;

public abstract class GameObject {
	/**
	 * Position of gameobject
	 */
	Coordinates coordinates;
	
	public Coordinates getCoordinates() {
		return this.coordinates;
	}
}
