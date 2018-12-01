package model.gameobjects;

import model.Coordinates;

public class Tree extends GameObject implements ImmovableObject {

	public Tree(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
