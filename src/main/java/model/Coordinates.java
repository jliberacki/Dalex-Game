package model;

/**
 * Coordinates for objects on map.
 * 
 * @author kuba
 *
 */
public class Coordinates {

	private int x;
	private int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object obj) {
		// self check
		if (this == obj)
			return true;
		// null check
		if (obj == null)
			return false;
		// type check and cast
		if (getClass() != obj.getClass())
			return false;
		Coordinates coordinates = (Coordinates) obj;
		return coordinates.getX() == this.x && coordinates.getY() == y;
	}

	@Override
	public int hashCode() {
		int tmp = (y + ((x + 1) / 2));
		return x + (tmp * tmp);
	}

	public boolean areCorrect(int mapSize) {
		boolean tooSmall = this.x < 0 || this.y < 0;
		boolean tooLarge = this.x >= mapSize || this.y >= mapSize;
		return !(tooSmall || tooLarge);
	}

	public boolean isInStraightLineWith(Coordinates other) {
		return ((other.getX() == this.getX()) || (other.getY() == this.getY()));
	}

	/**
	 * Returns sum of coordinates; does NOT change this object or the argument
	 */

	public Coordinates addCoordinates(Coordinates other) {
		int newX = this.x + other.getX();
		int newY = this.y + other.getY();
		return new Coordinates(newX, newY);
	}

	@Override
	public String toString() {
		return ("(" + Integer.toString(x) + "," + Integer.toString(y) + ")");
	}
	
	/**
	 * Return biggest diffrence comparing x and y.
	 * for example if A(1,1), B(3,7)
	 * it will return 7-1 because it is more than 3-1
	 * @param coordinates
	 * @return
	 */
	public int biggestDifference(Coordinates coordinates) {
		int xDiffrence = Math.abs(x - coordinates.getX());
		int yDiffrence = Math.abs(y - coordinates.getY());
		if (xDiffrence > yDiffrence)
			return xDiffrence;
		return yDiffrence;
	}

    public double distanceTo(Coordinates other) {

        double xDifference = other.getX() - this.getX();
        double yDifference = other.getY() - this.getY();

        xDifference = Math.pow(xDifference, 2);
        yDifference = Math.pow(yDifference, 2);

        return Math.sqrt(xDifference + yDifference);
    }

    public Direction directionTo(Coordinates other) {

        int verticalDifference = other.getY() - this.getY();
        int horizontalDifference = other.getX() - this.getX();

        if (verticalDifference == 0) {
            if (horizontalDifference > 0) {
                return Direction.EAST;
            } else {
                return Direction.WEST;
            }
        }

        if (horizontalDifference == 0) {
            if (verticalDifference > 0) {
                return Direction.NORTH;
            } else {
                return Direction.SOUTH;
            }
        }

        if (verticalDifference > 0) {
            if (horizontalDifference > 0) {
                return Direction.NORTHEAST;
            } else {
                return Direction.NORTHWEST;
            }
        }

        if (verticalDifference < 0) {
            if (horizontalDifference > 0) {
                return Direction.SOUTHEAST;
            } else {
                return Direction.SOUTHWEST;
            }
        }

        throw new IllegalArgumentException("Error in comparing objects' coordinates");
    }
}
