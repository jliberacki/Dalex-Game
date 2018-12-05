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
		Dalek.graph = createGraph();
	}

	/**
	 * Creates path @{link Graph} for {@link Dalek}s to find the best way to catch doctor.
	 */
	public Graph createGraph() {
		int mapSize = levelMap.getSize();
		Graph result = new Graph(mapSize);

		// Deleting edges when specific field cannot be reached
		levelMap
				.getMap()
				.values()
				.stream()
				.filter(x -> !x.reachable())
				.map(Field::getCoordinates)
				.forEach(result::deleteVertex);

		result.fillEdges();

		return result;
	}

	/**
	 * Moves doctor, moves {@link Dalek}s on map.
	 */
	public void executeRound() {
		Game.doctor.move(levelMap.getSize());
		Dalek.graph.refreshPaths(Game.doctor.getCoordinates());
		for (Field field : levelMap.getMap().values()) {
			for (Dalek dalek : field.getDaleks()) {
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
	 * Returns true if there is more than one {@link Dalek} on the map.
	 * 
	 * @return
	 */
	private boolean isMoreThanOneDalekAlive() {
		int numberOfDaleks;
		for (Field field : levelMap.getMap().values()) {
			numberOfDaleks = +field.numberOfDaleks();
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
		return isMoreThanOneDalekAlive() && !Game.doctor.hasBeenAttacked();
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
