package oop.model;

public class Earth extends AbstractWorldMap implements WorldMap {

    // do zaimplementowania
    // 1 warunek: nie wyjdz z mapy z gory i dołu
    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }
}
