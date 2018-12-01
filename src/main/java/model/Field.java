package model;

import java.util.ArrayList;
import java.util.List;

import model.gameobjects.Dalek;
import model.gameobjects.Junk;
import model.gameobjects.PowerUp;

public class Field {

	private Coordinates coordinates;
	private List<Dalek> daleks = new ArrayList<>();
	private PowerUp powerUp = null;
	private Junk junk = null;
	private int numberOfObjects;

	public Field(Coordinates coordinates) {
		this.coordinates = coordinates;
		this.numberOfObjects = 0;
	}

	public List<Dalek> getDaleks() {
		return daleks;
	}

	public PowerUp getPowerUp() {
		if (powerUp == null)
			return null;
		return powerUp;
	}

	public int numberOfDaleks() {
		return this.daleks.size();
	}

	public boolean hasPowerUp() {
		if (powerUp == null)
			return false;
		return true;
	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
		this.numberOfObjects++;
	}

	public void removePowerUp() {
		this.powerUp = null;
		this.numberOfObjects--;
	}

	public void addDalek(Dalek dalek) {
		daleks.add(dalek);
		this.numberOfObjects++;
	}

	public void removeDalek(Dalek dalek) {
		getDaleks().remove(dalek);
		this.numberOfObjects--;
	}

	public void removeAllDaleks() {
		this.numberOfObjects -= daleks.size();
		daleks.removeAll(daleks);
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public boolean hasDoctor() {
		return Game.doctor.getCoordinates().equals(this.coordinates);
	}

	public boolean hasJunk() {
		if (this.junk == null)
			return false;
		return true;
	}

	public void addJunk() {
		this.junk = new Junk(this.coordinates);
	}

	public void removeJunk() {
		this.junk = null;
	}

	public boolean doesCollisionHappen() {
		if (numberOfObjects > 1)
			return true;
		return false;
	}

}
