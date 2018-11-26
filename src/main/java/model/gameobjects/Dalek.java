package model.gameobjects;

import model.Graph;

public class Dalek extends MovableGameObject {
	private Graph graph;
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
	}

	public void attack(Doctor d) {
		System.out.println("doktor");
	}

	public void attack(Dalek d) {
		System.out.println("dalek");
	}

	public void attack(Junk junk) {
		System.out.println("junk");
	}
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
}
