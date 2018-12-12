package model.gameobjects;

import model.Coordinates;
import javafx.scene.image.Image;

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
	Image image;

	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
}
