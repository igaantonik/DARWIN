package oop.model;

import oop.MapVisualizer;

import java.util.UUID;

public class Earth extends AbstractWorldMap implements WorldMap {
    private MapVisualizer mapVisualizer;

    public Earth( WorldParameters worldParameters) {
        this.uuid = UUID.randomUUID();
        this.height = worldParameters.getMapHeight();
        this.width = worldParameters.getMapWidth();
        this.worldParameters = worldParameters;
        this.mapVisualizer = new MapVisualizer(this);
        placePlants(worldParameters.getStartPlantNumber());
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(width,height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.getY() >= 0 && position.getY() <= height - 1;
    }
}
