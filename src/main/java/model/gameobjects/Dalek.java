package model.gameobjects;

import model.CollisionResult;
import model.Graph;

public class Dalek extends MovableGameObject {
	private Graph graph;

	@Override
	public void move() {
		// TODO Auto-generated method stub
	}

	public CollisionResult attack(Doctor doctor) {
		CollisionResult collisionResult = new CollisionResult();
		doctor.decreaseHealth();
		collisionResult.addDalekToRemove(this);
		if (doctor.isAlive()) {
			collisionResult.setResult(doctor);
		} else {
			collisionResult.setResult(null);
			// moze powiadomic ze doktor nie zyje??????????
		}
		return collisionResult;
	}

	public CollisionResult attack(Dalek dalek) {
		CollisionResult collisionResult = new CollisionResult();
		collisionResult.addDalekToRemove(this);
		collisionResult.addDalekToRemove(dalek);
		collisionResult.setResult(new Junk(this.coordinates));
		return collisionResult;
	}

	public CollisionResult attack(Junk junk) {
		CollisionResult collisionResult = new CollisionResult();
		collisionResult.addDalekToRemove(this);
		collisionResult.setResult(new Junk(this.coordinates));
		return collisionResult;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
}
