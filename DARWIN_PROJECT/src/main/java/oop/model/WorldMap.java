package oop.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface WorldMap extends MoveValidator {

    boolean placeAnimal(Animal animal) throws PositionAlreadyOccupiedException;

    void move(Animal animal);

    boolean isOccupied(Vector2d position);

    boolean isPlant(Vector2d position);
    boolean isAnimal(Vector2d position);

    void deadAnimal(Animal animal);

    List animalAt(Vector2d position);

    MapElement plantAt(Vector2d position);
    String toString();

    Map<Vector2d, MapElement> getElements();

    UUID getId();
}