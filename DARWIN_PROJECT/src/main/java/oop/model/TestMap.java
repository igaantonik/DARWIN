package oop.model;


import oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestMap extends AbstractWorldMap implements WorldMap {
    private MapVisualizer mapVisualizer;
//    core.core.model.MapVisualizer mapVisualizer;

    public TestMap(int height, int width){
        this.uuid = UUID.randomUUID();
        this.height = height;
        this.width = width;
//        this.worldParameters = new WorldParameters();
        this.mapVisualizer = new  MapVisualizer(this);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(width,height));
    }
}
