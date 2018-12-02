package model;

import java.util.Map;

/**
 * Class which wraps map collecion and represents Map for game.
 * @author kuba
 *
 */
public class LevelMap {

	private Map<Coordinates, Field> map;
	int size;

	public LevelMap(int size, Map<Coordinates, Field> map) {
		this.map = map;
		this.size = size;
	}
	
	/**
	 * Returns size of map. Map is a squre - n x n fields. Returned size is this n.
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	public Map<Coordinates, Field> getMap() {
		return map;
	}
}
