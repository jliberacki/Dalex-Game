package model;

public enum Direction {
	/*
	 * -1,-1 | 0,-1 | 1,-1 -1, 0 | 0, 0 | 1, 0 -1, 1 | 0, 1 | 1, 1
	 */

	NorthWest(new Coordinates(-1, -1)), North(new Coordinates(0, -1)), NorthEast(new Coordinates(1, -1)),

	West(new Coordinates(-1, 0)), East(new Coordinates(1, 0)),

	SouthWest(new Coordinates(-1, 1)), South(new Coordinates(0, 1)), SouthEast(new Coordinates(1, 1));

	private Coordinates coordinates;

	Direction(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinates coordinates() {
		return this.coordinates;
	}
}