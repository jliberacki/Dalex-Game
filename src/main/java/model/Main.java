package model;

import java.util.ArrayList;
import java.util.List;

import model.gameobjects.Dalek;
import model.gameobjects.GameObject;

public class Main {

	public static void main(String[] args) {
		Dalek dalek1 = new Dalek();
		GameObject dalek2 = new Dalek();
		dalek1.attack(dalek2);
		List <GameObject> list = new ArrayList<>();
	}
}
