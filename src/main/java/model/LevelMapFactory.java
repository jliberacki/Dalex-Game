package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.gameobjects.Dalek;
import model.gameobjects.GameObject;
import model.gameobjects.LifePowerUp;
import model.gameobjects.PowerUp;
import model.gameobjects.Stone;
import model.gameobjects.Tree;

/**
 * Factory which is crated for easy map initialization.
 * 
 * @author kuba
 *
 */
public class LevelMapFactory {
	Random rand = new Random();

	/**
	 * Returns fully initialized HashMap with {@link Dalek}s, {@link Tree}s,
	 * {@link Stone}s and other {@link GameObject}s based on {@link Level} and
	 * sizeOfMap...
	 * 
	 * @param levelNumber
	 * @param sizeOfMap
	 * @return
	 */
	public LevelMap initializeMap(int levelNumber, int sizeOfMap) {
		Map<Coordinates, Field> map = new HashMap<>();
		for (int i = 0; i < sizeOfMap; i++) {
			for (int j = 0; j < sizeOfMap; j++) {
				map.put(new Coordinates(i, j), new Field(new Coordinates(i, j)));
			}
		}
		placeDoctor(map);
		placePowerUp(generateNumberOfPowerUps(levelNumber), map, sizeOfMap);
		placeDaleks(generateNumberOfDaleks(levelNumber), map, sizeOfMap);
		// placeStones(generateNumberOfStones(levelNumber), map, sizeOfMap);
		// placeTrees(generateNumberOfTrees(levelNumber), map, sizeOfMap);
		return new LevelMap(sizeOfMap, map);
	}

	/**
	 * place doctor on the map
	 * 
	 * @param sizeOfMap
	 */
	private void placeDoctor(Map<Coordinates, Field> map) {
		Game.doctor.setCoordinates(generateCoordinatesForDoctor(map));
	}

	/**
	 * Places {@link Dalek}s on map.
	 * 
	 * @param numberOfDaleks
	 * @param map
	 * @param sizeOfMap
	 */
	private void placeDaleks(int numberOfDaleks, Map<Coordinates, Field> map, int sizeOfMap) {
		for (int i = 0; i < numberOfDaleks; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map, sizeOfMap);
			Dalek dalek = new Dalek(coordinates);
			map.get(coordinates).addDalek(dalek);
			System.out.println(map.get(coordinates).daleksToString());
		}
	}

	/**
	 * Places {@link Tree}s on map.
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
	 * Places {@link Stone}s on map.
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
	 * Places {@link PowerUp}s on map.
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
	 * Generate number of {@link Dalek}s based on levelNumber.
	 * 
	 * @param levelNumber
	 * @return
	 */
	private int generateNumberOfDaleks(int levelNumber) {
		return levelNumber + rand.nextInt(5);
	}

	/**
	 * Generate number of {@link Tree}s based on levelNumber.
	 * 
	 * @param levelNumber
	 * @return
	 */
	private int generateNumberOfTrees(int levelNumber) {
		return levelNumber + rand.nextInt(3);
	}

	/**
	 * Generate number of {@link Stone}s based on levelNumber.
	 * 
	 * @param levelNumber
	 * @return
	 */
	private int generateNumberOfStones(int levelNumber) {
		return levelNumber + rand.nextInt(3);
	}

	/**
	 * Generate number of {@link PowerUp}s based on levelNumber.
	 * 
	 * @param levelNumber
	 * @return
	 */
	private int generateNumberOfPowerUps(int levelNumber) {
		return levelNumber + rand.nextInt(3);
	}

	/**
	 * Generates {@link Coordinates} for doctor.
	 * 
	 * @param sizeOfMap
	 * @return
	 */
	private Coordinates generateCoordinatesForDoctor(Map<Coordinates, Field> map) {
		List<Coordinates> keys = new ArrayList<Coordinates>(map.keySet());
		Coordinates randomCords = keys.get(rand.nextInt(keys.size()));
		return randomCords;
	}

	/**
	 * Generates {@link Coordinates} for any {@link GameObject} other than doctor.
	 * Coordinates are pointed at empty field.
	 * 
	 * @param map
	 * @param sizeOfMap
	 * @return
	 */
	private Coordinates generateCoordinatesForOther(Map<Coordinates, Field> map, int sizeOfMap) {
		List<Coordinates> keys = new ArrayList<Coordinates>(map.keySet());
		Coordinates randomCords = keys.get(rand.nextInt(keys.size()));

		while (thisFieldIsOccupiedByAnyObject(randomCords, map)) {
			randomCords = keys.get(rand.nextInt(keys.size()));
		}
		return randomCords;
	}

	/**
	 * Returns true is this {@link Coordinates} are occupied by any
	 * {@link GameObject} on this map.
	 * 
	 * @param coordinates
	 * @param map
	 * @return
	 */
	public boolean thisFieldIsOccupiedByAnyObject(Coordinates coordinates, Map<Coordinates, Field> map) {
		return map.get(coordinates).anyObjects();
	}

}
