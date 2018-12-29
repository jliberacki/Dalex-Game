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
		this.collisionHandler = new CollisionHandler();
	}

	/**
	 * Moves doctor, moves {@link Dalek}s on map.
	 */
	public LevelMap executeRound(Doctor doctor) {
		System.out.println("start of round:\n" + levelMap.toString());
		levelMap.getMap().get(doctor.getCoordinates()).removeDoctorFromThisField();
		doctor.move(levelMap);
		levelMap.getMap().get(doctor.getCoordinates()).addDoctorToThisField();
		boolean pathsCalculated = false;

		for (Dalek dalek : levelMap.getListOfAllDaleks()) {
			if (!pathsCalculated) {
				dalek.getGraph().calculatePaths(doctor.getCoordinates());
				pathsCalculated = true;
			}

			levelMap.getMap().get(dalek.getCoordinates()).removeDalek(dalek);

			dalek.move();

			levelMap.getMap().get(dalek.getCoordinates()).addDalek(dalek);
		}
		System.out.println("before handling collsions:\n" + levelMap.stringWithNumberOfObjects());
		this.roundScore += collisionHandler.handleCollisions(levelMap, doctor);
		System.out.println("end of round:\n" + levelMap.toString() + "\n" + "Doctor lifes: " + doctor.getHealth() + "\n round score: " + roundScore);
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
