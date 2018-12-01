package model.gameobjects;

import model.Coordinates;
import model.Graph;

public class Dalek extends MovableGameObject {
	private Graph graph;

	@Override
	public void move(int sizeOfMap) {
		// TODO Auto-generated method stub

	}

	public Dalek(Coordinates coordinates) {
		super.coordinates = coordinates;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
}
