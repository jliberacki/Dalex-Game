package model.gameobjects;

import model.Coordinates;

/**
 * Every object on map is GameObject.
 * 
 * @author kuba
 *
 */
public abstract class GameObject {
	/**
	 * Position of gameobject
	 */
	Coordinates coordinates;

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
}
