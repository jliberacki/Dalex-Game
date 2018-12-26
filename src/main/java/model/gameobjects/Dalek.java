package model.gameobjects;

import model.Coordinates;
import model.DalekGraph;
import model.Game;

/**
 * Class which represents dalek on map.
 */
public class Dalek extends GameObject implements MovableObject {

	public static DalekGraph graph;

	/**
	 * Moves dalek on map using DalekGraph (structure which gives him opportunity to
	 * find best way to doctor).
	 */

	public void move() {
		// TODO Babol zwraca NULLA!!!!!!!!!!!!!!!!!!
		// System.out.println(graph.nextStepToDoctor(this.coordinates));
		int currentX = this.getCoordinates().getX();
		int currentY = this.getCoordinates().getY();
		int newX = currentX;
		int newY = currentY;

		if (currentX > Game.doctor.getCoordinates().getX()) {
			newX = currentX - 1;
		} else if (currentX < Game.doctor.getCoordinates().getX()) {
			newX = currentX + 1;
		}

		if (currentY > Game.doctor.getCoordinates().getY()) {
			newY = currentY - 1;
		} else if (currentY < Game.doctor.getCoordinates().getY()) {
			newY = currentY + 1;
		}
		this.coordinates = new Coordinates(newX, newY);
	}

	public Dalek(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
