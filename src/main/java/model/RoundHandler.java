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
	private Doctor doctor;

	/**
	 * Initializes RoundHandler.
	 * 
	 * @param daleksNumber
	 */
	public RoundHandler(LevelMap levelMap, Doctor doctor) {
		this.levelMap = levelMap;
		Dalek.graph = createDalekGraph();
		this.collisionHandler = new CollisionHandler(doctor);
		this.doctor = doctor;
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
	public void executeRound() {
		System.out.println("start of round:\n" + levelMap.toString());
		levelMap.getMap().get(doctor.getCoordinates()).removeDoctorFromThisField();
		this.doctor.move(levelMap.coordinatesAvailableForTeleport());
		levelMap.getMap().get(doctor.getCoordinates()).doctorOnThisField();
		Dalek.graph.calculatePaths(this.doctor.getCoordinates());
		for (Dalek dalek : levelMap.getListOfAllDaleks()) {
			levelMap.getMap().get(dalek.getCoordinates()).removeDalek(dalek);
			dalek.move(doctor.getCoordinates());
			levelMap.getMap().get(dalek.getCoordinates()).addDalek(dalek);
		}
		System.out.println("before handling collsions:\n" + levelMap.stringWithNumberOfObjects());
		collisionHandler.handleCollisions(levelMap);
		System.out.println("end of round:\n" + levelMap.toString());
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
	private boolean isMoreThanZeroDaleksAlive() {
		int numberOfDaleks = 0;
		for (Field field : levelMap.getMap().values()) {
			numberOfDaleks += field.numberOfDaleks();
			if (numberOfDaleks > 0)
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
		return isMoreThanZeroDaleksAlive() && !this.doctor.hasBeenAttacked();
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
