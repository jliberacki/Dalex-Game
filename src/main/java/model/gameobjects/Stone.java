package model.gameobjects;

import model.Coordinates;

public class Stone extends GameObject implements ImmovableObject {

	public Stone(Coordinates coordinates) {
		super.coordinates = coordinates;
	}
}
