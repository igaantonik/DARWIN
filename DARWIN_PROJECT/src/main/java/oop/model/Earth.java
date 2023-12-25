package oop.model;

public class Earth extends AbstractWorldMap implements WorldMap {

    // do zaimplementowania
    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }
}
