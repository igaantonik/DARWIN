package oop.model;

import java.util.UUID;

public class Earth extends AbstractWorldMap implements WorldMap {

    public Earth(int height, int width) {
        this.uuid = UUID.randomUUID();
        this.height = height;
        this.width = width;
        this.worldParameters = new WorldParameters();
        placePlants(worldParameters.getStartPlantNumber());
    }

    // do zaimplementowania
    // 1 warunek: nie wyjdz z mapy z gory i dołu -> DONE
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.getY() >= 0 && position.getY() <= height - 1;
    }
}
