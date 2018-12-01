package model;

import model.gameobjects.Dalek;

/**
 * 
 * @author kuba
 *
 */
public class RoundHandler {
	private int roundScore;
	private CollisionHandler collisionHandler;
	private LevelMap levelMap;

	/**
	 * Initializes RoundHandler.
	 * 
	 * @param size
	 * @param daleksNumber
	 */
	public RoundHandler(LevelMap levelMap) {
		this.levelMap = levelMap;
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
		for (Field field : levelMap.getMap().values()) {
			for (Dalek dalek : field.getDaleks()) {
				dalek.setGraph(createGraph());
				dalek.move();
				levelMap.getMap().get(dalek.getCoordinates()).addDalek(dalek);
				field.removeDalek(dalek);
			}
		}
		collisionHandler.handleCollisions(levelMap);
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
		for (Field field : levelMap.getMap().values()) {
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
