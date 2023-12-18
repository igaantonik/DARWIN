package core.model;


import core.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestMap extends AbstractWorldMap implements WorldMap {
    private final int height;
    private final int width;
    private Vector2d lower_left;
    private Vector2d upper_right;
    private MapVisualizer mapVisualizer;
    Map<Vector2d, Animal> animals;
//    core.core.model.MapVisualizer mapVisualizer;

    public TestMap(int height, int width){
        this.uuid = UUID.randomUUID();
        this.height = height;
        this.width = width;
        this.lower_left = new Vector2d(0,0);
        this.upper_right = new Vector2d(width,height);
        this.animals = new HashMap<>();
        this.mapVisualizer = new  MapVisualizer(this);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if( !(position.follows(lower_left) && position.precedes(upper_right))){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(lower_left, upper_right);
    }
}
