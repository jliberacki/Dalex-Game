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
	 * @param daleksNumber
	 */
	public RoundHandler(LevelMap levelMap) {
		this.levelMap = levelMap;
	}

	/**
	 * Creates path @{link Graph} for {@link Dalek}s to find the best way to catch doctor.
	 */
	public Graph createGraph() {
		return null;
		// TODO
	}

	/**
	 * Moves doctor, moves {@link Dalek}s on map.
	 */
	public void executeRound() {
		for (Field field : levelMap.getMap().values()) {
			for (Dalek dalek : field.getDaleks()) {
				dalek.move(levelMap.getSize(), createGraph());
				levelMap.getMap().get(dalek.getCoordinates()).addDalek(dalek);
				field.removeDalek(dalek);
			}
		}
		collisionHandler.handleCollisions(levelMap);
      	Game.doctor.moved=true;
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
	 * Returns true if there is more than one {@link Dalek} on the map.
	 * 
	 * @return
	 */
	private boolean isMoreThanOneDalekAlive() {
		int numberOfDaleks;
		for (Field field : levelMap.getMap().values()) {
			numberOfDaleks += field.numberOfDaleks();
			if (numberOfDaleks > 1)
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
		return isMoreThanOneDalekAlive() && !Game.doctor.hasBeenAttacked() && Game.doctor.hasDoctorMoved();
	}

	/**
	 * Creates new round with copy of it self.
	 * 
	 * @return
	 */
	public RoundHandler roundSnapshot() {
		Game.roundEnded(this.levelMap);
		return this;
	}
}
