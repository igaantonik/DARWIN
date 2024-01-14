package oop.model;

import java.util.List;

public interface Genom {
    void randomGenes();
    void mutation();

    MapDirection changeDirection(MapDirection currDirection);

    Vector2d changePosition(Vector2d vector2d, MapDirection direction);

    void nextGen();

    List<Gen> getGenes();

    boolean equal(AbstractGenom genom);

}
