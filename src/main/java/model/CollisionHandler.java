package model;

import model.gameobjects.Doctor;

/**
 * Chooses strategy to solve conflicts during collision.
 * 
 * @author kuba
 *
 */
public class CollisionHandler {
	private ScoreCounter scoreCounter;

	/**
	 * Constructor
	 * 
	 * @param doctor
	 */
	public CollisionHandler() {
		scoreCounter = new ScoreCounter();
	}

	/**
	 * Iterates through map, if collision is deteceted on any {@link Field} it is
	 * solved by solveCollision method.
	 * 
	 * @param levelMap
	 * @return
	 */
	public int handleCollisions(LevelMap levelMap, Doctor doctor) {
		int score = 0;
		for (Field field : levelMap.getMap().values()) {
			if (field.doesCollisionHappen()) {
				solveCollision(field, doctor, score);
			}
		}
		return score;
	}

	/**
	 * Takes as an argument the {@link Field} where collision happened and solves
	 * it.
	 * 
	 * @param field
	 */
	private void solveCollision(Field field, Doctor doctor, int score) {
		System.out.println("collision detected at " + field.getCoordinates());
		if (field.hasDoctor()) {
			solveCollisionWithDoctor(field, doctor, score);
		} else {
			solveCollisionWithoutDoctor(field, score);
		}
	}

	/**
	 * solves collision if there is doctor on this {@link Field}.
	 * 
	 * @param field
	 */
	private void solveCollisionWithDoctor(Field field, Doctor doctor, int score) {
		if (field.hasPowerUp()) {
			field.getPowerUp().powerUp(doctor);
			field.removePowerUp();
			score += this.scoreCounter.powerUpPickUp();
		}
		if (field.numberOfDaleks() > 0) {
			doctor.decreaseHealth();
		}
	}

	/**
	 * Solves collision if there isn't doctor on this {@link Field}.
	 * 
	 * @param field
	 */
	private void solveCollisionWithoutDoctor(Field field, int score) {
		if (field.numberOfDaleks() > 0) {
			if (field.hasPowerUp())
				field.removePowerUp();
			if (field.hasJunk()) {
				score += this.scoreCounter.dalekVsJunk(field.numberOfDaleks());
				field.removeAllDaleks();
			} else if (field.numberOfDaleks() > 1) {
				score += this.scoreCounter.dalekVsDalek(field.numberOfDaleks());
				field.removeAllDaleks();
				field.setJunk();
			}
		}
	}
}
