package model.gameobjects;

import model.Coordinates;
import model.DalekGraph;
import model.Field;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class DestroyerDalek extends Dalek {

    private LinkedList<Field> powerupFields;
    public DalekGraph destroyerGraph;

    @Override
    public void move() {

        powerupFields = powerupFields.stream()
                .filter(Field::hasPowerUp)
                .sorted((x, y) -> {
                    double distanceToX = x.getCoordinates().distanceTo(this.coordinates);
                    double distanceToY = y.getCoordinates().distanceTo(this.coordinates);
                    if (distanceToX == distanceToY) return 0;
                    return (distanceToX > distanceToY) ? 1 : -1;
                })
                .collect(Collectors.toCollection(LinkedList::new));

        powerupFields.stream()
                .map(Field::getCoordinates)
                .forEach(System.out::println);

        if (powerupFields.isEmpty()) {
            this.coordinates = graph.nextStepToTarget(this.coordinates);
        } else {
            Field target = powerupFields.removeFirst();
            destroyerGraph.calculatePaths(target.getCoordinates());
            this.coordinates = destroyerGraph.nextStepToTarget(this.coordinates);
        }

    }

    public DestroyerDalek(Coordinates coordinates, DalekGraph dalekGraph, DalekGraph destroyerGraph) {
        super(coordinates, dalekGraph);
        this.destroyerGraph = destroyerGraph;
    }
}
