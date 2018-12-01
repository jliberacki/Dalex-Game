package model.gameobjects;

public class Doctor extends MovableGameObject {

	private int health;
	private boolean attacked;

	public Doctor(int health) {
		this.health = health;
		this.attacked = false;
	}

	public boolean isAlive() {
		if (health > 0)
			return true;
		return false;
	}

	public void decreaseHealth() {
		this.health--;
		this.attacked = true;
	}

	public void increaseHealth(int healthToAdd) {
		this.health = this.health + healthToAdd;
	}

	public int getHealth() {
		return health;
	}

	/**
	 * returns true if doctor was attacked.
	 * 
	 * @return
	 */
	public boolean hasBeenAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	/**
	 * Reads player's input, verifies it and moves the doctor accordingly.
	 */

	public void move(int sizeOfMap) {

	}
}
