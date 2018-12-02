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

	/**
	 * Moves dalek on map using Graph (structure which gives him opportunity to find
	 * best way to doctor) and size of map (to not move outside the map).
	 * 
	 * @param sizeOfMap
	 * @param graph
	 */
	public void move(int sizeOfMap, Graph graph) {
	}

	public Dalek(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
