package model;

import model.gameobjects.Dalek;
import model.gameobjects.Doctor;

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
	public RoundHandler(LevelMap levelMap, Doctor doctor) {
		this.levelMap = levelMap;
		Dalek.graph = createDalekGraph();
		this.collisionHandler = new CollisionHandler(doctor);
	}

	/**
	 * Creates path @{link DalekGraph} for {@link Dalek}s to find the best way to
	 * catch doctor.
	 */
	public DalekGraph createDalekGraph() {

		int mapSize = levelMap.getSize();
		DalekGraph result = new DalekGraph(mapSize);

		// Deleting edges when specific field cannot be reached
		levelMap.getMap().values().stream().filter(x -> !x.isReachable()).map(Field::getCoordinates)
				.forEach(result::deleteVertex);

		result.fillEdges();

		return result;
	}

	/**
	 * Moves doctor, moves {@link Dalek}s on map.
	 */
	public LevelMap executeRound(Doctor doctor) {
		System.out.println("start of round:\n" + levelMap.toString());
		levelMap.getMap().get(doctor.getCoordinates()).removeDoctorFromThisField();
		doctor.move(levelMap);
		levelMap.getMap().get(doctor.getCoordinates()).addDoctorToThisField();
		Dalek.graph.calculatePaths(doctor.getCoordinates());
		for (Dalek dalek : levelMap.getListOfAllDaleks()) {
			levelMap.getMap().get(dalek.getCoordinates()).removeDalek(dalek);
			dalek.move(doctor.getCoordinates());
			levelMap.getMap().get(dalek.getCoordinates()).addDalek(dalek);
		}
		System.out.println("before handling collsions:\n" + levelMap.stringWithNumberOfObjects());
		collisionHandler.handleCollisions(levelMap);
		System.out.println("end of round:\n" + levelMap.toString());
		return levelMap;
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
	 * Creates new round with copy of it self.
	 * 
	 * @return
	 */
	public RoundHandler roundSnapshot() {
		// Game.roundEnded(this.levelMap);
		return this;
	}
}
