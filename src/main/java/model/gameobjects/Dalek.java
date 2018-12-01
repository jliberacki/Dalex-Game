package model.gameobjects;

import model.Coordinates;
import model.Graph;

public class Dalek extends GameObject implements MovableObject {

	public void move(int sizeOfMap, Graph graph) {
	}

	public Dalek(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
