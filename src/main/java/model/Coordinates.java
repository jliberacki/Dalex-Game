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
		if (coordinates.getX() == this.x && coordinates.getY() == y)
			return true;
		return false;
	}
}
