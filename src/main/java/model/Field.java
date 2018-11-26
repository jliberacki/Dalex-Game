package model;

import java.util.ArrayList;
import java.util.List;

import model.gameobjects.Dalek;
import model.gameobjects.PowerUp;

public class Field {

	List<Dalek> daleks = new ArrayList<>();
	PowerUp powerUp = null;

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

}
