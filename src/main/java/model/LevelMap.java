package model;

import model.gameobjects.Dalek;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Field> getListOfPowerUpFields() {
        return map.values()
                .stream()
                .filter(Field::hasPowerUp)
                .collect(Collectors.toList());
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
	 * howFarFromDaleks - means how far from nearest daleks doctor will be
	 * teleported.
	 * 
	 * @return
	 */
	public List<Coordinates> coordinatesAvailableForTeleport(Coordinates doctorsCoordinates, int howFarFromDaleks) {
		List<Coordinates> toRemove = new ArrayList<>();
		List<Coordinates> coordinatesAvailableForTeleport = this.getListOfFreeCoordinates();
		List<Dalek> daleks = this.getListOfAllDaleks();

		for (Dalek dalek : daleks) {
			for (Coordinates coordinates : coordinatesAvailableForTeleport) {
				if (coordinates.biggestDifference(dalek.getCoordinates()) < howFarFromDaleks)
					toRemove.add(coordinates);
			}
			for (Coordinates coordinates : toRemove) {
				coordinatesAvailableForTeleport.remove(coordinates);
			}
			toRemove.clear();
		}

		if (coordinatesAvailableForTeleport.size() == 0) {
			return getListOfFreeCoordinates();
		}

		// boolean found = false;
		// String output = "";
		// for (int i = 9; i >= 0; i--) {
		// for (int j = 0; j < 10; j++) {
		// for (Coordinates coordinates : coordinatesAvailableForTeleport) {
		// if (coordinates.getX() == j && coordinates.getY() == i) {
		// found = true;
		// output = output + " + ";
		// }
		// }
		// if (!found) {
		// output = output + " . ";
		// }
		// found = false;
		// }
		// output = output + "\n";
		// }
		// System.out.println(output);

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
