package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.gameobjects.Dalek;
import model.gameobjects.Doctor;
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
	private Random rand = new Random();
	private List<Coordinates> availableCoordinates;
	private DalekGraph dalekGraph;

	/**
	 * Returns fully initialized HashMap with {@link Dalek}s, {@link Tree}s,
	 * {@link Stone}s and other {@link GameObject}s based on {@link Level} and
	 * sizeOfMap...
	 * 
	 * @param levelNumber
	 * @param sizeOfMap
	 * @return
	 */
	public LevelMap initializeMap(int levelNumber, int sizeOfMap, Doctor doctor) {
		availableCoordinates = new ArrayList<>();
		Map<Coordinates, Field> map = new HashMap<>();
		for (int i = 0; i < sizeOfMap; i++) {
			for (int j = 0; j < sizeOfMap; j++) {
				map.put(new Coordinates(i, j), new Field(new Coordinates(i, j)));
				availableCoordinates.add(new Coordinates(i, j));
			}
		}
		placeDoctor(map, doctor);
		placePowerUp(generateNumberOfPowerUps(levelNumber), map, sizeOfMap);
		placeDaleks(generateNumberOfDaleks(levelNumber), map, sizeOfMap);
		// placeStones(generateNumberOfStones(levelNumber), map, sizeOfMap);
		// placeTrees(generateNumberOfTrees(levelNumber), map, sizeOfMap);
		updateDalekGraph(map);
		return new LevelMap(sizeOfMap, map);
	}

	/**
	 * place doctor on the map
	 * 
	 * @param sizeOfMap
	 */
	private void placeDoctor(Map<Coordinates, Field> map, Doctor doctor) {
		doctor.setCoordinates(generateCoordinatesForDoctor(map));
	}

	/**
	 * Places {@link Dalek}s on map.
	 * 
	 * @param numberOfDaleks
	 * @param map
	 * @param sizeOfMap
	 */
	private void placeDaleks(int numberOfDaleks, Map<Coordinates, Field> map, int sizeOfMap) {

		this.dalekGraph = new DalekGraph(sizeOfMap);

		for (int i = 0; i < numberOfDaleks; i++) {
			Coordinates coordinates = generateCoordinatesForOther(map, sizeOfMap);
			Dalek dalek = new Dalek(coordinates, dalekGraph);
			map.get(coordinates).addDalek(dalek);
			// System.out.println(map.get(coordinates).daleksToString());
		}
	}

	/**
	 * Creates path @{link DalekGraph} for {@link Dalek}s to find the best way to
	 * catch doctor.
	 */
	private void updateDalekGraph(Map<Coordinates, Field> map) {

		// Deleting edges when specific field cannot be reached
		map.values().stream().filter(x -> !x.isReachable()).map(Field::getCoordinates)
				.forEach(dalekGraph::deleteVertex);

		dalekGraph.fillEdges();
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
		return levelNumber + rand.nextInt(5) + 1;
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
		Coordinates randomCords = availableCoordinates.get(rand.nextInt(availableCoordinates.size()));
		map.get(randomCords).addDoctorToThisField();
		availableCoordinates.remove(randomCords);
		refactorAvailableCoordinates(randomCords, 3);
		return randomCords;
	}

	/**
	 * When doctor has his own position it deletes available coords for other
	 * objects, thanks to this other objects like daleks wont be set very close to
	 * doctor.
	 * 
	 * @param coordinates
	 */
	private void refactorAvailableCoordinates(Coordinates coordinates, int howFarFromDoctor) {
		List<Coordinates> toRemove = new ArrayList<>();
		for (Coordinates coordinates2 : availableCoordinates) {
			if (coordinates2.biggestDifference(coordinates) < howFarFromDoctor)
				toRemove.add(coordinates2);
		}
		for (Coordinates coordinates2 : toRemove) {
			availableCoordinates.remove(coordinates2);
		}
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
		Coordinates randomCords = availableCoordinates.get(rand.nextInt(availableCoordinates.size()));

		while (thisFieldIsOccupiedByAnyObject(randomCords, map)) {
			randomCords = availableCoordinates.get(rand.nextInt(availableCoordinates.size()));
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
