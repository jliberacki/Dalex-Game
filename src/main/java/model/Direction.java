package model;

public enum Direction {
	/*
	 * -1,-1 | 0,-1 | 1,-1 -1, 0 | 0, 0 | 1, 0 -1, 1 | 0, 1 | 1, 1
	 */

	NORTHWEST(new Coordinates(-1, -1)), NORTH(new Coordinates(0, -1)), NORTHEAST(new Coordinates(1, -1)),

	WEST(new Coordinates(-1, 0)), EAST(new Coordinates(1, 0)),

	SOUTHWEST(new Coordinates(-1, 1)), SOUTH(new Coordinates(0, 1)), SOUTHEAST(new Coordinates(1, 1));

	private Coordinates coordinates;

	Direction(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinates coordinates() {
		return this.coordinates;
	}
}