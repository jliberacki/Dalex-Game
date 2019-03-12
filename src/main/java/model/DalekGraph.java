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

    public Coordinates nextStepToTarget(Coordinates dalekCoordinates) {

		int vertexNumber = coordinateToVertex(dalekCoordinates);
		int currentTravelCost = fieldPathLength[vertexNumber];

		Direction[] directions;

		// Order of directions is crucial

        if (dalekCoordinates.isInStraightLineWith(sourceCoordinates)) {
            directions = new Direction[]{EAST, WEST, NORTH, SOUTH, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST};
        } else if (dalekCoordinates.directionTo(sourceCoordinates) == NORTHWEST) {
            directions = new Direction[]{NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST, NORTH, WEST, EAST, SOUTH};

        } else if (dalekCoordinates.directionTo(sourceCoordinates) == NORTHEAST) {
            directions = new Direction[]{NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, NORTH, EAST, WEST, SOUTH};

        } else if (dalekCoordinates.directionTo(sourceCoordinates) == SOUTHEAST) {
            directions = new Direction[]{SOUTHEAST, NORTHEAST, SOUTHWEST, NORTHWEST, SOUTH, EAST, WEST, NORTH};

        } else {
            directions = new Direction[]{SOUTHWEST, NORTHWEST, SOUTHEAST, NORTHEAST, SOUTH, WEST, EAST, NORTH};
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

        throw new IllegalArgumentException("Dalek move error");
	}

}
