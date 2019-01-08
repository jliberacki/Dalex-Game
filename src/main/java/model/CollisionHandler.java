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
	private int score;

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
		this.score = 0;
		for (Field field : levelMap.getMap().values()) {
			if (field.doesCollisionHappen()) {
				solveCollision(field, doctor);
			}
		}
		return this.score;
	}

	/**
	 * Takes as an argument the {@link Field} where collision happened and solves
	 * it.
	 * 
	 * @param field
	 */
	private void solveCollision(Field field, Doctor doctor) {
//		System.out.println("collision detected at " + field.getCoordinates());
		if (field.hasDoctor()) {
			solveCollisionWithDoctor(field, doctor);
		} else {
			solveCollisionWithoutDoctor(field);
		}
	}

	/**
	 * solves collision if there is doctor on this {@link Field}.
	 * 
	 * @param field
	 */
	private void solveCollisionWithDoctor(Field field, Doctor doctor) {
		if (field.hasPowerUp()) {
			field.getPowerUp().powerUp(doctor);
			field.removePowerUp();
			this.score += this.scoreCounter.powerUpPickUp();
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
	private void solveCollisionWithoutDoctor(Field field) {
		if (field.numberOfDaleks() > 0) {
			if (field.hasPowerUp())
				field.removePowerUp();
			if (field.hasJunk()) {
				this.score += this.scoreCounter.dalekVsJunk(field.numberOfDaleks());
				field.removeAllDaleks();
			} else if (field.numberOfDaleks() > 1) {
				this.score += this.scoreCounter.dalekVsDalek(field.numberOfDaleks());
				field.removeAllDaleks();
				field.setJunk();
			}
		}
	}
}
