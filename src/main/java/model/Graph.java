package model;

import java.util.LinkedList;

/**
 * Class representing graph which is used by {@link MovableObject}s to find
 * paths. Currently only implements BFS but it can be expanded.
 */

public class Graph {

	protected int numberOfVertices;
	protected int mapSize;
	// lista sąsiednich wierzchołków
	protected LinkedList<Integer> adjListArray[];
	protected LinkedList<Integer> deletedVertices = new LinkedList<>();
	protected int[] fieldPathLength;
	protected Coordinates sourceCoordinates;

	public Graph(int mapSize) {
        this.mapSize = mapSize;
		this.numberOfVertices = mapSize * mapSize;

		fieldPathLength = new int[numberOfVertices];

		adjListArray = new LinkedList[numberOfVertices];

        initializeAdjListArrays();

		fillEdges();
	}

    private void initializeAdjListArrays() {
        for (int i = 0; i < numberOfVertices; i++) {
            adjListArray[i] = new LinkedList<>();
        }
    }


	/**
	 * Adds an edge from src to dest
	 */
	protected void addEdge(int src, int dest) {
		this.adjListArray[src].add(dest);
	}

	/**
	 * Deletes vertex from graph (to be more specific - isolates given vertex, so it
	 * cannot be reached)
	 */
	public void deleteVertex(Coordinates coordinates) {
		int deletedVertex = coordinateToVertex(coordinates);
		if (!deletedVertices.contains(deletedVertex)) {
			deletedVertices.add(deletedVertex);

			// Delete every edge leading to deleted vertex

			for (Integer adjacentVertex : adjListArray[deletedVertex]) {
				adjListArray[adjacentVertex].removeFirstOccurrence(deletedVertex);
			}

			// Delete every edge that comes out of deleted vertex

			adjListArray[deletedVertex].clear();
		}
	}

	/**
	 * Fills every edge between all adjacent nodes
	 */
	public void fillEdges() {
		for (int y = 0; y < mapSize; y++) {
			for (int x = 0; x < mapSize; x++) {
				Coordinates coordinates = new Coordinates(x, y);
				int vertexSrc = coordinateToVertex(coordinates);

				if (deletedVertices.contains(vertexSrc)) {
					continue;
				}

				Direction[] directions = Direction.values();

				for (Direction direction : directions) {
					Coordinates destCoordinates = new Coordinates(x, y);

					destCoordinates = destCoordinates.addCoordinates(direction.coordinates());

					if (destCoordinates.areCorrect(mapSize)) {
						int vertexDest = coordinateToVertex(destCoordinates);
						if (!deletedVertices.contains(vertexDest)) {
							addEdge(vertexSrc, vertexDest);
						}
					}
				}
			}
		}
	}

	/**
	 * Calculates path length of every vertex to the source.
	 */
	public void calculatePaths(Coordinates sourceCoordinates) {
		cleanFieldPathLength();
		this.sourceCoordinates = sourceCoordinates;
		int sourceVertex = coordinateToVertex(sourceCoordinates);

		boolean visitedField[] = new boolean[numberOfVertices];

		LinkedList<Integer> vertexQueue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visitedField[sourceVertex] = true;
		fieldPathLength[sourceVertex] = 0;
		vertexQueue.add(sourceVertex);

		while (vertexQueue.size() != 0) {

			int currentVertex = vertexQueue.pop();

			for (Integer neighbour : adjListArray[currentVertex]) {
				if (deletedVertices.contains(neighbour)) {
					continue;
				}

				if (!visitedField[neighbour]) {
					visitedField[neighbour] = true;
					fieldPathLength[neighbour] = fieldPathLength[currentVertex] + 1;
					vertexQueue.add(neighbour);
				}
			}
		}
	}

	/**
	 * "Zeroes" every path length
	 */
	private void cleanFieldPathLength() {
		for (int i = 0; i < fieldPathLength.length; i++) {
			fieldPathLength[i] = -1;
		}
	}

	/**
	 * Maps coordinates to vertex number We assume coordinates are in range [0,
	 * mapSize - 1]
	 */
	protected int coordinateToVertex(Coordinates coordinates) {
		return coordinates.getX() * mapSize + coordinates.getY();
	}
}
