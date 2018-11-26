package model.gameobjects;

public class Doctor extends MovableGameObject {

	private int health;

	public Doctor(int health) {
		this.health = health;
	}

	public boolean isAlive() {
		if (health > 0)
			return true;
		return false;
	}
	/**
	 * 
	 * @return
	 */
	public boolean decreaseHealth() {
		if (health > 0) {
			this.health--;
			return true;
		}
		return false;
	}

	public void increaseHealth(int healthToAdd) {
		this.health = this.health + healthToAdd;
	}

	/**
	 * Reads player's input, verifies it and moves the doctor accordingly.
	 */

	public int getHealth() {
		return health;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
	}
}
