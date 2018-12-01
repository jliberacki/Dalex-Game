package model;

import java.util.HashMap;
import java.util.Map;

import model.gameobjects.Dalek;
import model.gameobjects.LifePowerUp;
import model.gameobjects.Stone;
import model.gameobjects.Tree;

public class MapInitializer {

	/**
	 * Returns fully initialized map with daleks, trees, stones and others...
	 * 
	 * @param levelNumber
	 * @param sizeOfMap
	 * @return
	 */
	public Map<Coordinates, Field> initializeMap(int levelNumber, int sizeOfMap) {
		Map<Coordinates, Field> map = new HashMap<>();
		for (int i = 0; i < sizeOfMap; i++) {
			for (int j = 0; i < sizeOfMap; j++) {
				map.put(new Coordinates(i, j), new Field(new Coordinates(i, j)));
			}
		}
		placeDoctor();
		placePowerUp(generateNumberOfPowerUs(levelNumber), map);
		placeDaleks(generateNumberOfDaleks(levelNumber), map);
		placeStones(generateNumberOfStones(levelNumber), map);
		placeTrees(generateNumberOfTrees(levelNumber), map);
		return map;
	}

	private void placeDoctor() {
		Game.doctor.setCoordinates(generateCoordinatesForDoctor());
	}

	private void placeDaleks(int numberOfDaleks, Map<Coordinates, Field> map) {
		for (int i = 0; i < numberOfDaleks; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map);
			map.get(coordinates).addDalek(new Dalek(coordinates));
		}
	}

	private void placeTrees(int numberOfTrees, Map<Coordinates, Field> map) {
		for (int i = 0; i < numberOfTrees; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map);
			map.get(coordinates).setTree(new Tree(coordinates));
		}
	}

	private void placeStones(int numberOfStones, Map<Coordinates, Field> map) {
		for (int i = 0; i < numberOfStones; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map);
			map.get(coordinates).setStone(new Stone(coordinates));
		}
	}

	private void placePowerUp(int numberOfPowerUps, Map<Coordinates, Field> map) {
		for (int i = 0; i < numberOfPowerUps; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map);
			map.get(coordinates).setPowerUp(new LifePowerUp(coordinates));
		}
	}

	private int generateNumberOfDaleks(int levelNumber) {
		return 10;
	}

	private int generateNumberOfTrees(int levelNumber) {
		return 2;
	}

	private int generateNumberOfStones(int levelNumber) {
		return 2;
	}

	private int generateNumberOfPowerUs(int levelNumber) {
		return 2;
	}

	private Coordinates generateCoordinatesForDoctor() {
		return new Coordinates(1, 2);
	}

	private Coordinates generateCoordinatesForOther(Map<Coordinates, Field> map) {
		return new Coordinates(2, 3);
	}

	/**
	 * Returns true is this coordinates are occupied by any object on this map.
	 * 
	 * @param coordinates
	 * @param map
	 * @return
	 */
	public boolean checkIfThisFieldIsOccupied(Coordinates coordinates, Map<Coordinates, Field> map) {
		return map.get(coordinates).anyObjects();
	}

}
