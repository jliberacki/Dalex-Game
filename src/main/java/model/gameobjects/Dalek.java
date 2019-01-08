package model.gameobjects;

import model.Coordinates;
import model.DalekGraph;

/**
 * Class which represents dalek on map.
 */
public class Dalek extends GameObject implements MovableObject {

    public DalekGraph graph;

	/**
	 * Moves dalek on map using DalekGraph (structure which gives him opportunity to
	 * find best way to doctor).
	 */

    public void move() {
        this.coordinates = graph.nextStepToDoctor(this.coordinates);
    }

    public Dalek(Coordinates coordinates, DalekGraph dalekGraph) {
		super.coordinates = coordinates;
        this.graph = dalekGraph;
    }

    public DalekGraph getGraph() {
        return graph;
    }
}
