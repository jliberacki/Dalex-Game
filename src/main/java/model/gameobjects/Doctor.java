package model.gameobjects;

/**
 * Class which represent Doctor on the map.
 * 
 * @author kuba
 *
 */
public class Doctor extends GameObject implements MovableObject {

	private int health;
	private boolean attacked;

	public Doctor(int health) {
		this.health = health;
		this.attacked = false;
	}

	/**
	 * Returns true if doctor has more than 0 lifes.
	 * 
	 * @return
	 */
	public boolean isAlive() {
		if (health > 0)
			return true;
		return false;
	}

	/**
	 * Decreases health when doctor is attaced for example by dalek.
	 */
	public void decreaseHealth() {
		this.health--;
		this.attacked = true;
	}

	/**
	 * Increases health of the doctor.
	 * 
	 * @param healthToAdd
	 */
	public void increaseHealth(int healthToAdd) {
		this.health = this.health + healthToAdd;
	}

	/**
	 * Returns actual points of helath of doctor.
	 * 
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * returns true if doctor was attacked, false otherwise.
	 * 
	 * @return
	 */
	public boolean hasBeenAttacked() {
		return attacked;
	}

	/**
	 * Gives opportunity to reset field attaec for example if doctor has to be no
	 * more considered as attacek.
	 * 
	 * @param attacked
	 */
	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	/**
	 * Reads player's input, verifies it and moves the doctor accordingly.
	 */
	public void move(int sizeOfMap) {

	}
}
