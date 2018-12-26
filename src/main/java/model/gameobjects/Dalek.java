package model.gameobjects;

import model.Coordinates;
import model.DalekGraph;

/**
 * Class which represents dalek on map.
 */
public class Dalek extends GameObject implements MovableObject {

	public static DalekGraph graph;

	/**
	 * Moves dalek on map using DalekGraph (structure which gives him opportunity to
	 * find best way to doctor).
	 */

	public void move(Coordinates doctorCoordinates) {
		// TODO Babol zwraca NULLA!!!!!!!!!!!!!!!!!!
		// System.out.println(graph.nextStepToDoctor(this.coordinates));
		int currentX = this.getCoordinates().getX();
		int currentY = this.getCoordinates().getY();
		int newX = currentX;
		int newY = currentY;

		if (currentX > doctorCoordinates.getX()) {
			newX = currentX - 1;
		} else if (currentX < doctorCoordinates.getX()) {
			newX = currentX + 1;
		}

		if (currentY > doctorCoordinates.getY()) {
			newY = currentY - 1;
		} else if (currentY < doctorCoordinates.getY()) {
			newY = currentY + 1;
		}
		this.coordinates = new Coordinates(newX, newY);
	}

	public Dalek(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
