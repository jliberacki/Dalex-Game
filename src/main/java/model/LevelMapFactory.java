package model;

import java.util.HashMap;
import java.util.Map;

import model.gameobjects.Dalek;
import model.gameobjects.LifePowerUp;
import model.gameobjects.Stone;
import model.gameobjects.Tree;

/**
 * Factory which is crated for easy map initialization.
 * 
 * @author kuba
 *
 */
public class LevelMapFactory {

	/**
	 * Returns fully initialized HashMap with daleks, trees, stones and others based
	 * on level and sizeOfMap...
	 * 
	 * @param levelNumber
	 * @param sizeOfMap
	 * @return
	 */
	public LevelMap initializeMap(int levelNumber, int sizeOfMap) {
		Map<Coordinates, Field> map = new HashMap<>();
		for (int i = 0; i < sizeOfMap; i++) {
			for (int j = 0; i < sizeOfMap; j++) {
				map.put(new Coordinates(i, j), new Field(new Coordinates(i, j)));
			}
		}
		placeDoctor(sizeOfMap);
		placePowerUp(generateNumberOfPowerUps(levelNumber), map, sizeOfMap);
		placeDaleks(generateNumberOfDaleks(levelNumber), map, sizeOfMap);
		placeStones(generateNumberOfStones(levelNumber), map, sizeOfMap);
		placeTrees(generateNumberOfTrees(levelNumber), map, sizeOfMap);
		return new LevelMap(sizeOfMap, map);
	}

	/**
	 * place doctor on the map
	 * 
	 * @param sizeOfMap
	 */
	private void placeDoctor(int sizeOfMap) {
		Game.doctor.setCoordinates(generateCoordinatesForDoctor(sizeOfMap));
	}

	/**
	 * 
	 * @param numberOfDaleks
	 * @param map
	 * @param sizeOfMap
	 */
	private void placeDaleks(int numberOfDaleks, Map<Coordinates, Field> map, int sizeOfMap) {
		for (int i = 0; i < numberOfDaleks; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map, sizeOfMap);
			map.get(coordinates).addDalek(new Dalek(coordinates));
		}
	}

	/**
	 * 
	 * @param numberOfTrees
	 * @param map
	 * @param sizeOfMap
	 */
	private void placeTrees(int numberOfTrees, Map<Coordinates, Field> map, int sizeOfMap) {
		for (int i = 0; i < numberOfTrees; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map, sizeOfMap);
			map.get(coordinates).setTree(new Tree(coordinates));
		}
	}

	/**
	 * Places PowerUps on map.
	 * 
	 * @param numberOfStones
	 * @param map
	 * @param sizeOfMap
	 */
	private void placeStones(int numberOfStones, Map<Coordinates, Field> map, int sizeOfMap) {
		for (int i = 0; i < numberOfStones; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map, sizeOfMap);
			map.get(coordinates).setStone(new Stone(coordinates));
		}
	}

	/**
	 * Places PowerUps on map.
	 * 
	 * @param numberOfPowerUps
	 * @param map
	 * @param sizeOfMap
	 */
	private void placePowerUp(int numberOfPowerUps, Map<Coordinates, Field> map, int sizeOfMap) {
		for (int i = 0; i < numberOfPowerUps; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map, sizeOfMap);
			map.get(coordinates).setPowerUp(new LifePowerUp(coordinates));
		}
	}

	/**
	 * Generate number of Daleks based on levelNumber
	 * 
	 * @param levelNumber
	 * @return
	 */
	private int generateNumberOfDaleks(int levelNumber) {
		return levelNumber * 5;
	}

	/**
	 * Generate number of Trees based on levelNumber
	 * 
	 * @param levelNumber
	 * @return
	 */
	private int generateNumberOfTrees(int levelNumber) {
		return levelNumber;
	}

	/**
	 * Generate number of Stones based on levelNumber
	 * 
	 * @param levelNumber
	 * @return
	 */
	private int generateNumberOfStones(int levelNumber) {
		return levelNumber;
	}

	/**
	 * Generate number of PowerUps based on levelNumber
	 * 
	 * @param levelNumber
	 * @return
	 */
	private int generateNumberOfPowerUps(int levelNumber) {
		return levelNumber;
	}

	/**
	 * Generates coordinates for doctor
	 * 
	 * @param sizeOfMap
	 * @return
	 */
	private Coordinates generateCoordinatesForDoctor(int sizeOfMap) {
		return new Coordinates(sizeOfMap, sizeOfMap);
	}

	/**
	 * Generates coordinates for any object other than doctor. Coordinates are
	 * pointed at empty field.
	 * 
	 * @param map
	 * @param sizeOfMap
	 * @return
	 */
	private Coordinates generateCoordinatesForOther(Map<Coordinates, Field> map, int sizeOfMap) {
		Coordinates coordinates = new Coordinates(sizeOfMap, sizeOfMap);
		while (thisFieldIsOccupiedByAnyObject(coordinates, map)) {
			coordinates = new Coordinates(sizeOfMap, sizeOfMap);
		}
		return coordinates;
	}

	/**
	 * Returns true is this coordinates are occupied by any object on this map.
	 * 
	 * @param coordinates
	 * @param map
	 * @return
	 */
	public boolean thisFieldIsOccupiedByAnyObject(Coordinates coordinates, Map<Coordinates, Field> map) {
		return map.get(coordinates).anyObjects();
	}

}
