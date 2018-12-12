package model.gameobjects;

import model.Coordinates;
import model.DalekGraph;

/**
 * Class which represents dalek on map.
 */
public class Dalek extends GameObject implements MovableObject {

	public static DalekGraph graph;

	/**
	 * Moves dalek on map using DalekGraph (structure which gives him opportunity to find
	 * best way to doctor).
	 */

	public void move() {
		this.coordinates = graph.nextStepToDoctor(this.coordinates);
	}

	public Dalek(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
