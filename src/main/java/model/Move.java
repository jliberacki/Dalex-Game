package model;
import model.gameobjects.Doctor;
import view.Drawer;

public class Move {
	
	private Drawer drawer;
	private Doctor doctor;
	
	public Move(Drawer drawer, Doctor doctor) {
		this.drawer = drawer;
		this.doctor = doctor;
	}
	
	public boolean validateMove(String move) {
		switch (move) {
		case "u":
			return doctor.getCoordinates().getY() < drawer.size - 1;
		case "d":
			return doctor.getCoordinates().getY() > 0;
		case "l":
			return doctor.getCoordinates().getX() > 0;
		case "r":
			return doctor.getCoordinates().getX() < drawer.size - 1;
		case "ul":
			return (doctor.getCoordinates().getY() < drawer.size - 1) && (doctor.getCoordinates().getX() > 0);
		case "ur":
			return (doctor.getCoordinates().getY() < drawer.size - 1) && (doctor.getCoordinates().getX() < drawer.size - 1);
		case "dr":
			return (doctor.getCoordinates().getY() > 0) && (doctor.getCoordinates().getX() < drawer.size - 1);
		case "dl":
			return (doctor.getCoordinates().getY() > 0) && (doctor.getCoordinates().getX() > 0);
		case "t":
			return true;
		default:
			return false;
		}
	}
	
//	currentLevel.play(doctor, "u");
//	LevelMap levelMapToDraw = currentLevel.getLevelMap();
//	drawer.drawObjects(levelMapToDraw);
	
	public void executeMove(Level currentLevel, String move) {
		currentLevel.play(doctor, move);
		LevelMap levelMapToDraw = currentLevel.getLevelMap();
		drawer.drawObjects(levelMapToDraw);
	}
}
