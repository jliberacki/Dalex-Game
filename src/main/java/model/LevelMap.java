package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.gameobjects.Dalek;

/**
 * Class which wraps map collecion and represents Map for game.
 * 
 * @author kuba
 *
 */
public class LevelMap {

	private Map<Coordinates, Field> map;
	int size;

	public LevelMap(int size, Map<Coordinates, Field> map) {
		this.map = map;
		this.size = size;
	}

	/**
	 * Returns size of map. Map is a squre - n x n fields. Returned size is this n.
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns Map<Coordinates, Field>
	 * 
	 * @return
	 */
	public Map<Coordinates, Field> getMap() {
		return map;
	}

	/**
	 * Returns list of daleks in whole map.
	 * 
	 * @return
	 */
	public List<Dalek> getListOfAllDaleks() {
		List<Dalek> allDaleks = new ArrayList<>();
		for (Field field : map.values()) {
			for (Dalek dalek : field.getDaleks()) {
				allDaleks.add(dalek);
			}
		}
		return allDaleks;
	}

	/**
	 * Returns number of daleks on map.
	 * 
	 * @return
	 */
	public int countDaleks() {
		int numberOfDaleks = 0;
		for (Field field : map.values()) {
			numberOfDaleks += field.numberOfDaleks();
		}
		return numberOfDaleks;
	}

	/**
	 * Returns true if there is more than one {@link Dalek} on the map.
	 * 
	 * @return
	 */
	public boolean isMoreThanZeroDaleksAlive() {
		int numberOfDaleks = 0;
		for (Field field : map.values()) {
			numberOfDaleks += field.numberOfDaleks();
			if (numberOfDaleks > 0)
				return true;
		}
		return false;
	}

	/**
	 * Returns list of Coordinates where there are no objects.
	 * 
	 * @return
	 */
	public List<Coordinates> getListOfFreeCoordinates() {
		List<Coordinates> freeCoordinates = new ArrayList<>();
		for (Field field : map.values()) {
			if (field.isEmpty()) {
				freeCoordinates.add(field.getCoordinates());
			}
		}
		return freeCoordinates;
	}

	/**
	 * Returns list of coordinates on which doctor can be teleported.
	 * howFarFromDales - means how far from nearest daleks doctor will be
	 * teleported.
	 * 
	 * @return
	 */
	public List<Coordinates> coordinatesAvailableForTeleport(Coordinates doctorsCoordinates, int howFarFromDaleks) {
		List<Coordinates> toRemove = new ArrayList<>();
		List<Coordinates> coordinatesAvailableForTeleport = getListOfFreeCoordinates();

		for (Coordinates coordinates : coordinatesAvailableForTeleport) {
			if (coordinates.biggestDifference(doctorsCoordinates) < howFarFromDaleks)
				toRemove.add(coordinates);
		}

		for (Coordinates coordinates : toRemove) {
			coordinatesAvailableForTeleport.remove(coordinates);
		}

		return coordinatesAvailableForTeleport;
	}

	@Override
	public String toString() {
		String output = "";
		for (int y = size - 1; y >= 0; y--) {
			for (int x = 0; x < size; x++) {
				if (x == 0) {
					output = output + " " + Integer.toString(y) + " ";
				}
				Field field = map.get(new Coordinates(x, y));
				if (field.hasDoctor()) {
					output = output + " D ";
				} else if (field.hasDalek()) {
					output = output + " x ";
				} else if (field.hasStone()) {
					output = output + " o ";
				} else if (field.hasTree()) {
					output = output + " t ";
				} else if (field.hasPowerUp()) {
					output = output + " ! ";
				} else if (field.hasJunk()) {
					output = output + " j ";
				} else {
					output = output + " . ";
				}
			}
			output = output + "\n";
		}
		output = output + "    0  1  2  3  4  5  6  7  8  9 \n";
		output = output + "number of daleks: " + Integer.toString(getListOfAllDaleks().size()) + "\n";
		return output;
	}

	public String stringWithNumberOfObjects() {
		String output = "";
		for (int y = size - 1; y >= 0; y--) {
			for (int x = 0; x < size; x++) {
				if (x == 0) {
					output = output + " " + Integer.toString(y) + " ";
				}
				Field field = map.get(new Coordinates(x, y));
				if (field.getNumberOfObjects() != 0)
					output = output + " " + Integer.toString(field.getNumberOfObjects()) + " ";
				else
					output = output + " . ";
			}
			output = output + "\n";
		}
		output = output + "    0  1  2  3  4  5  6  7  8  9 \n";
		output = output + "number of daleks: " + Integer.toString(getListOfAllDaleks().size()) + "\n";
		return output;
	}
}
