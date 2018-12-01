package model;

import java.util.Map;

public class LevelMap {

	private Map<Coordinates, Field> map;
	int size;

	public LevelMap(int size, Map<Coordinates, Field> map) {
		this.map = map;
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	public Map<Coordinates, Field> getMap() {
		return map;
	}
}
