package model;

import java.util.Map;

import model.gameobjects.Dalek;

/**
 * 
 * @author kuba
 *
 */
public class RoundHandler {
	private int roundScore;
	private CollisionHandler collisionHandler;
	private Map<Coordinates, Field> map;
	private int sizeOfMap;

	/**
	 * Initializes RoundHandler.
	 * 
	 * @param size
	 * @param daleksNumber
	 */
	public RoundHandler(int sizeOfMap, Map<Coordinates, Field> map) {
		this.sizeOfMap = sizeOfMap;
		this.map = map;
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
	 * returns true if nex round can be executed, otherwise false
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
