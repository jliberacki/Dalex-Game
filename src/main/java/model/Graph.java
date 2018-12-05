package model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class which represents graph which is used by {@link MovableObject}s to find
 * the best way to doctor.
 *
 * @author kuba
 */
public class Graph {

    private int V;
    private int mapSize;
    private LinkedList<Integer> adjListArray[];
    private LinkedList<Integer> deletedVertices = new LinkedList<>();
    private int[] fieldPathLength;
    private Coordinates doctorCoordinates;

    public Graph(int numberOfVertices) {
        this.V = numberOfVertices * numberOfVertices;
        this.mapSize = numberOfVertices;
        fieldPathLength = new int[V];

        adjListArray = new LinkedList[V];

        for (int i = 0; i < V; i++) {
            adjListArray[i] = new LinkedList<>();
        }

        fillEdges();
    }

    // Adds an edge from src to dest.
    private void addEdge(int src, int dest) {
        this.adjListArray[src].add(dest);
    }

    // Deletes vertex from graph (to be more specific - isolates given vertex, so it cannot be reached)
    public void deleteVertex(Coordinates coordinates) {
        int deletedVertex = coordinateToVertex(coordinates);
        if (!deletedVertices.contains(deletedVertex)) {
            deletedVertices.add(deletedVertex);
        }
    }

    // Fills every edge between all adjacent nodes
    public void fillEdges() {
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                Coordinates coordinates = new Coordinates(x, y);

                int[] offsetX = {-1, -1, -1, 0, 0, 1, 1, 1};
                int[] offsetY = {-1, 0, 1, -1, 1, 1, 0, -1};

                int vertexSrc = coordinateToVertex(coordinates);

                if (deletedVertices.contains(vertexSrc)) {
                    continue;
                }

                for (int pos = 0; pos < 8; pos++) {
                    Coordinates destCoordinates = new Coordinates(x + offsetX[pos], y + offsetY[pos]);

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

    // Returns coordinates which are the next step in path to Doctor
    public Coordinates nextStepToDoctor(Coordinates dalekCoordinates) {
        int x = dalekCoordinates.getX();
        int y = dalekCoordinates.getY();
        int doctorX = doctorCoordinates.getX();
        int doctorY = doctorCoordinates.getY();

        int vertexNumber = coordinateToVertex(dalekCoordinates);
        int currentTravelCost = fieldPathLength[vertexNumber];

        int[] offsetX = {-1, -1, 1, 1, -1, 0, 0, 1};
        int[] offsetY = {-1, 1, 1, -1, 0, -1, 1, 0};

        if (x == doctorX || y == doctorY) {
            offsetX = new int[]{-1, 0, 0, 1, -1, -1, 1, 1};
            offsetY = new int[]{0, -1, 1, 0, -1, 1, 1, -1};
        }

		/*
			-1,-1  |  0,-1  |  1,-1
			-1, 0  |  0, 0  |  1, 0
			-1, 1  |  0, 1  |  1, 1
		*/

        for (int pos = 0; pos < offsetX.length; pos++) {
            Coordinates destCoordinates = new Coordinates(x + offsetX[pos], y + offsetY[pos]);
            int destVertex = coordinateToVertex(destCoordinates);

            if (destCoordinates.areCorrect(mapSize) && adjListArray[vertexNumber].contains(destVertex)) {
                int vertexDest = coordinateToVertex(destCoordinates);

                if (fieldPathLength[vertexDest] < currentTravelCost) {
                    return destCoordinates;
                }
            }
        }

        // Error
        return null;
    }

    // Refreshes path length of every vertex to the source (Doctor).
    public void refreshPaths(Coordinates doctorCoordinates) {
        cleanFieldPathLength();
        this.doctorCoordinates = doctorCoordinates;
        int doctorVertex = coordinateToVertex(doctorCoordinates);

        boolean visitedField[] = new boolean[V];

        LinkedList<Integer> vertexQueue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visitedField[doctorVertex] = true;
        fieldPathLength[doctorVertex] = 0;
        vertexQueue.add(doctorVertex);

        while (vertexQueue.size() != 0) {

            int currentVertex = vertexQueue.poll();

            Iterator<Integer> neighbourIterator = adjListArray[currentVertex].listIterator();

            while (neighbourIterator.hasNext()) {
                int neighbour = neighbourIterator.next();
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

    // "Zeroes" every path length
    private void cleanFieldPathLength() {
        for (int i = 0; i < fieldPathLength.length; i++) {
            fieldPathLength[i] = -1;
        }
    }

    // We assume coordinates are in range [0, mapSize - 1]
    // Translates coordinates to vertex number
    private int coordinateToVertex(Coordinates coordinates) {
        return coordinates.getX() * mapSize + coordinates.getY();
    }
}
