package model.gameobjects;

import model.Coordinates;

public class Junk extends GameObject implements ImmovableObject {

	public Junk(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
