package model.gameobjects;

import model.Coordinates;
import model.DalekGraph;

public class StandardDalek extends Dalek {

    public StandardDalek(Coordinates coordinates, DalekGraph dalekGraph) {
        super(coordinates, dalekGraph);
    }

    public void move() {
        this.coordinates = graph.nextStepToTarget(this.coordinates);
    }

}
