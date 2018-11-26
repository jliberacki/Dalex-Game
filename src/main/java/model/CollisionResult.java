package model;

import java.util.LinkedList;
import java.util.List;

import model.gameobjects.GameObject;

public class CollisionResult {
	private List<GameObject> toRemove = new LinkedList<>();

	GameObject result;

	public List<GameObject> getToRemove() {
		return toRemove;
	}

	public GameObject getResult() {
		return result;
	}

	public void setResult(GameObject result) {
		this.result = result;
	}

	public void addToToRemove(GameObject gameObject) {
		this.toRemove.add(gameObject);
	}

}
