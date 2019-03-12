package model;

import model.gameobjects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent one {@link Field} on map on which other objects can stay.
 * 
 * @author kuba
 *
 */
public class Field {

	private Coordinates coordinates;
	private List<Dalek> daleks = new ArrayList<>();
	private PowerUp powerUp = null;
	private Junk junk = null;
	private Tree tree = null;
	private Stone stone = null;
	private int numberOfObjects;
	private boolean doctorIsOnThisField;

	public Field(Coordinates coordinates) {
		this.coordinates = coordinates;
		this.numberOfObjects = 0;
	}

	public List<Dalek> getDaleks() {
		return daleks;
	}

	public boolean isEmpty() {
		return numberOfObjects == 0;
	}

	public PowerUp getPowerUp() {
		if (powerUp == null)
			return null;
		return powerUp;
	}

	/**
	 * Returns numberOfDaleks on this {@link Field}.
	 * 
	 * @return
	 */
	public int numberOfDaleks() {
		return this.daleks.size();
	}

	/**
	 * returns true if {@link Field} has {@link PowerUp}, otherwise false.
	 * 
	 * @return
	 */
	public boolean hasPowerUp() {
		if (powerUp == null)
			return false;
		return true;
	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
		this.numberOfObjects++;
	}

	/**
	 * Removes {@link PowerUp} from this {@link Field}.
	 */
	public void removePowerUp() {
		this.powerUp = null;
		this.numberOfObjects--;
	}

	public void addDalek(Dalek dalek) {
		daleks.add(dalek);
		this.numberOfObjects++;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	/**
	 * Returns true if there is {@link Tree} on this {@link Field}, otherwise false.
	 * 
	 * @return
	 */
	public boolean hasTree() {
		if (this.tree == null)
			return false;
		return true;
	}

	public void setStone(Stone stone) {
		this.stone = stone;
	}

	/**
	 * Returns true if there is {@link Stone} on this {@link Field}, otherwise
	 * false.
	 * 
	 * @return
	 */
	public boolean hasStone() {
		if (this.stone == null)
			return false;
		return true;
	}

	public void removeDalek(Dalek dalek) {
		getDaleks().remove(dalek);
		this.numberOfObjects--;
	}

	public void removeAllDaleks() {
		this.numberOfObjects -= daleks.size();
		daleks.clear();
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public int getNumberOfObjects() {
		return numberOfObjects;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Returns true it doctor is on this {@link Field}, otherwise false.
	 * 
	 * @return
	 */
	public boolean hasDoctor() {
		return doctorIsOnThisField;
	}

	/**
	 * run if doctor is on this field
	 */
	public void addDoctorToThisField() {
		numberOfObjects++;
		doctorIsOnThisField = true;
	}

	/**
	 * Doctor is no longer on this field
	 */
	public void removeDoctorFromThisField() {
		numberOfObjects--;
		doctorIsOnThisField = false;
	}

	/**
	 * Returns true if there is {@link Junk} on this {@link Field}, otherwise false.
	 * 
	 * @return
	 */
	public boolean hasJunk() {
		if (this.junk == null)
			return false;
		return true;
	}

	public void setJunk() {
		this.junk = new Junk(this.coordinates);
		numberOfObjects++;
	}

	public void removeJunk() {
		this.junk = null;
		numberOfObjects--;
	}

	/**
	 * Returns true if collision happend on this {@link Field}, otherwise false.
	 * 
	 * @return
	 */
	public boolean doesCollisionHappen() {
		if (numberOfObjects > 1)
			return true;
		return false;
	}

	/**
	 * Returns true if there is one or more {@link GameObject}s on this
	 * {@link Field}
	 * 
	 * @return
	 */
	public boolean anyObjects() {
		if (numberOfObjects > 0) {
			return true;
		}
		return false;
	}

	public boolean hasDalek() {
		if (this.daleks.size() == 1)
			return true;
		return false;
	}

	/**
	 * Returns true if you can stay on this field
	 * 
	 * @return
	 */
	public boolean isReachable() {
        return !(hasTree() || hasStone());
	}

	/**
	 * Returns string with daleks staying on this field
	 * 
	 * @return
	 */
	public String daleksToString() {
		if (daleks.isEmpty())
			return "empty";
		String output = "";
		for (Dalek dalek : daleks)
			output = output + ", (" + dalek + "," + dalek.getCoordinates() + ")";
		return output;
	}
}
