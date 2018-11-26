package model.gameobjects;

import model.Coordinates;

public class Dalek extends MovableGameObject {
	public static Coordinates doctorCoords;

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
}
