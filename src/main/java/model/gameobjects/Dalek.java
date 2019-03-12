package model.gameobjects;

import model.Coordinates;
import model.DalekGraph;

/**
 * Class which represents dalek on map.
 */
public abstract class Dalek extends GameObject implements MovableObject {

    protected DalekGraph graph;

	/**
	 * Moves dalek on map using DalekGraph (structure which gives him opportunity to
	 * find best way to doctor).
	 */

    public abstract void move();

    public Dalek(Coordinates coordinates, DalekGraph dalekGraph) {
		super.coordinates = coordinates;
        this.graph = dalekGraph;
    }

    public DalekGraph getGraph() {
        return graph;
    }
}
