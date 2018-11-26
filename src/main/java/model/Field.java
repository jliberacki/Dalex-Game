package model;

import java.util.ArrayList;
import java.util.List;

import model.gameobjects.Dalek;
import model.gameobjects.PowerUp;

public class Field {

	private Coordinates coordinates;

	List<Dalek> daleks = new ArrayList<>();
	PowerUp powerUp = null;

	public Field(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public List<Dalek> getDaleks() {
		return daleks;
	}

	public PowerUp getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(PowerUp powerUp) {
		this.powerUp = powerUp;
	}

	public void addDalek(Dalek dalek) {
		daleks.add(dalek);
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public boolean doesCollisionHappen() {
		int numberOfObjects = 0;
		numberOfObjects = numberOfObjects + daleks.size();
		if (powerUp != null)
			numberOfObjects++;
		if (Game.doctor.getCoordinates().equals(this.coordinates))
			numberOfObjects++;
		if (numberOfObjects > 1)
			return true;
		return false;
	}
	
	public void removeDalek(Dalek dalek) {
		getDaleks().remove(dalek);
	}
	
	public void removePowerUp() {
		this.powerUp = null;
	}
}
