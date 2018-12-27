package model.gameobjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import model.Coordinates;
import model.LevelMap;

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

	public void move(LevelMap levelMap) {
		String input = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (input.matches("u")) {
			this.coordinates = new Coordinates(this.coordinates.getX(), this.coordinates.getY() + 1);
		} else if (input.matches("ul")) {
			this.coordinates = new Coordinates(this.coordinates.getX() - 1, this.coordinates.getY() + 1);
		} else if (input.matches("ur")) {
			this.coordinates = new Coordinates(this.coordinates.getX() + 1, this.coordinates.getY() + 1);
		} else if (input.matches("d")) {
			this.coordinates = new Coordinates(this.coordinates.getX(), this.coordinates.getY() - 1);
		} else if (input.matches("dl")) {
			this.coordinates = new Coordinates(this.coordinates.getX() - 1, this.coordinates.getY() - 1);
		} else if (input.matches("dr")) {
			this.coordinates = new Coordinates(this.coordinates.getX() + 1, this.coordinates.getY() - 1);
		} else if (input.matches("l")) {
			this.coordinates = new Coordinates(this.coordinates.getX() - 1, this.coordinates.getY());
		} else if (input.matches("r")) {
			this.coordinates = new Coordinates(this.coordinates.getX() + 1, this.coordinates.getY());
		} else {
			Random rand = new Random();
			List<Coordinates> coordinatesAvailableForTeleport = levelMap
					.coordinatesAvailableForTeleport(this.coordinates);
			this.coordinates = coordinatesAvailableForTeleport
					.get(rand.nextInt(coordinatesAvailableForTeleport.size()));
		}

	}
}
