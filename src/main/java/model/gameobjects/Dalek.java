package model.gameobjects;

import model.Coordinates;
import model.Graph;

/**
 * Class which represents dalek on map.
 * 
 * @author kuba
 *
 */
public class Dalek extends GameObject implements MovableObject {

	public static Graph graph;

	/**
	 * Moves dalek on map using Graph (structure which gives him opportunity to find
	 * best way to doctor).
	 * 
	 */
	public void move() {
		Coordinates newCoordinates = graph.nextStepToDoctor(this.coordinates);
		this.coordinates = newCoordinates;
	}

	public Dalek(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
