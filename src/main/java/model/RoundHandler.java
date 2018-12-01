package model;

import java.util.HashMap;
import java.util.Map;

import model.gameobjects.Dalek;

/**
 * 
 * @author kuba
 *
 */
public class RoundHandler {
	private int roundScore;
	private int size;
	private CollisionHandler collisionHandler;
	private Map<Coordinates, Field> map;

	/**
	 * Initiales map based on daleksNumber and size of map. Places all objects on
	 * map.
	 * 
	 * @param size
	 * @param daleksNumber
	 */
	public RoundHandler(int size, int daleksNumber) {
		this.size = size;
		placeDaleks(daleksNumber);
		initializeMap();
	}

	/**
	 * Initializing fields in map, and adding objects there.
	 */
	private void initializeMap() {
		this.map = new HashMap<Coordinates, Field>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Coordinates coordinates = new Coordinates(i, j);
				this.map.put(coordinates, new Field(coordinates));
				// TODO adding objects on map (daleks, trees, stones, doctor etc);
			}
		}
	}

	/**
	 * Creates path graph for daleks to find the best way to catch doctor.
	 */
	public Graph createGraph() {
		return null;
		// TODO
	}

	/**
	 * Moves doctor, moves daleks on map.
	 */
	public void executeRound() {
		Game.doctor.move();
		for (Field field : map.values()) {
			for (Dalek dalek : field.getDaleks()) {
				dalek.setGraph(createGraph());
				dalek.move();
				map.get(dalek.getCoordinates()).addDalek(dalek);
				field.removeDalek(dalek);
			}
		}
		collisionHandler.handleCollisions(map);
	}

	/**
	 * Places daleks (and in future other objects) on map.
	 * 
	 * @param daleksNumber
	 */
	private void placeDaleks(int daleksNumber) {
		// TODO
		Game.doctor.move();
	}

	/**
	 * Returns roundScore and clears it to prepare for a new round.
	 * 
	 * @return
	 */
	public int getAndClearRoundScore() {
		int score = roundScore;
		this.roundScore = 0;
		return score;
	}

	/**
	 * Returns true if all daleks are dead and false otherwise.
	 * 
	 * @return
	 */
	private boolean areAnyDaleksAlive() {
		for (Field field : map.values()) {
			if (field.numberOfDaleks() > 0)
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean nextRoundCanBeExecuted() {
		return areAnyDaleksAlive() && !Game.doctor.hasBeenAttacked();
	}

	/**
	 * Creates new round with copy of it self.
	 * 
	 * @return
	 */
	public RoundHandler roundSnapshot() {
		// TODO
		return null;
	}
}
