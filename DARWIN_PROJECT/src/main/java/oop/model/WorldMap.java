package oop.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface WorldMap extends MoveValidator {

    // listeners
    void addObserver(MapChangeListener observer);

    void dayRoutine();
    //Daily events Plant - DO ZROBIENIA
    void dailyPlantGrow();

    void placePlants(int plantsAmount);
    //Daily events Plant
    boolean placePlant(Plant plant);

    void removePlant(Plant plant);

    boolean placeAnimal(Animal animal);

    void move(Animal animal);

    boolean isOccupied(Vector2d position);

    void moveAllAnimals();

    void dinner();

    void reproduction();

    void lookForDeadAnimals();

    void sortAliveAnimalsInVector(Vector2d position);

    boolean isPlant(Vector2d position);
    boolean isAnimal(Vector2d position);

    void removeAnimal(Animal animal);

    void deadAnimal(Animal animal);

    List animalAt(Vector2d position);

    MapElement plantAt(Vector2d position);
    String toString();

    Map<Vector2d, MapElement> getElements();

    UUID getId();
}