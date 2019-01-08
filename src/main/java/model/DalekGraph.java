package model;

import model.gameobjects.Dalek;

import static model.Direction.*;

/**
 * Class which represents graph which is used by {@link Dalek}s to find the best
 * way to doctor.
 */

public class DalekGraph extends Graph {

    public DalekGraph(int mapSize) {
        super(mapSize);
	}

	/**
	 * Returns coordinates which are the next step in path to the Doctor
	 */

	public Coordinates nextStepToDoctor(Coordinates dalekCoordinates) {

		int vertexNumber = coordinateToVertex(dalekCoordinates);
		int currentTravelCost = fieldPathLength[vertexNumber];

		Direction[] directions;

		// Order of directions is crucial

		if (dalekCoordinates.isInStraightLineWith(sourceCoordinates)) {
            directions = new Direction[]{EAST, WEST, NORTH, SOUTH, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST};
		} else {
            directions = new Direction[]{NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST, EAST, WEST, NORTH, SOUTH};
		}

		for (Direction direction : directions) {
			Coordinates destCoordinates = dalekCoordinates.addCoordinates(direction.coordinates());
			int destVertex = coordinateToVertex(destCoordinates);

			if (destCoordinates.areCorrect(mapSize) && adjListArray[vertexNumber].contains(destVertex)) {
				int vertexDest = coordinateToVertex(destCoordinates);

				if (fieldPathLength[vertexDest] < currentTravelCost) {
					return destCoordinates;
				}
			}
		}

        throw new IllegalArgumentException("Bad move?");
	}

}
