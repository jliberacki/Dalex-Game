package model;

import java.util.LinkedList;
import java.util.List;

import model.gameobjects.Dalek;
import model.gameobjects.GameObject;

public class CollisionResult {
	private List<Dalek> daleksToRemove = new LinkedList<>();
	private boolean powerUpRemove = false;

	GameObject result;

	public List<Dalek> getDaleksToRemove() {
		return daleksToRemove;
	}

	public GameObject getResult() {
		return result;
	}

	public void setResult(GameObject result) {
		this.result = result;
	}

	public void addPowerUpToRemove() {
		this.powerUpRemove = true;
	}

	public void addDalekToRemove(Dalek dalek) {
		this.daleksToRemove.add(dalek);
	}

	public boolean isPowerUpToRemove() {
		return this.powerUpRemove;
	}

}
