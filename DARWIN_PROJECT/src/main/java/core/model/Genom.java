package core.model;

public interface Genom {
    void mutation();

    MapDirection changeDirection(MapDirection currDirection);

    Vector2d changePosition(Vector2d vector2d, MapDirection direction);

    void nextGen();

}
