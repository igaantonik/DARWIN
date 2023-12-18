package core.model;

import core.model.*;

import java.util.Map;
import java.util.UUID;

public interface WorldMap extends MoveValidator {

    boolean placeAnimal(Animal animal) throws PositionAlreadyOccupiedException;

    void move(Animal animal);

    boolean isOccupied(Vector2d position);

    MapElement objectAt(Vector2d position);
    String toString();

    Map<Vector2d, MapElement> getElements();

    UUID getId();
}