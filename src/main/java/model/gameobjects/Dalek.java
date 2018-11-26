package model.gameobjects;

import model.CollisionResult;
import model.Graph;

public class Dalek extends MovableGameObject {
	private Graph graph;

	@Override
	public void move() {
		// TODO Auto-generated method stub
	}

	public CollisionResult attack(Doctor d) {
		return null;
	}

	public CollisionResult attack(Dalek dalek) {
		CollisionResult collisionResult = new CollisionResult();
		collisionResult.addDalekToRemove(this);
		collisionResult.addDalekToRemove(dalek);
		collisionResult.setResult(new Junk(this.coordinates));
		return collisionResult;
	}

	public CollisionResult attack(Junk junk) {
		System.out.println("junk");
		return null;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
}
