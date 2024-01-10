package oop.model;

import oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FlowsAndEbbs extends AbstractWorldMap implements WorldMap{
    private Map<Vector2d, Plant> basins = new HashMap<>();
    private MapVisualizer mapVisualizer;
    public FlowsAndEbbs(int height, int width) {
        this.uuid = UUID.randomUUID();
        this.height = worldParameters.getMapHeight();
        this.width = worldParameters.getMapWidth();
        this.worldParameters = worldParameters;
        this.mapVisualizer = new MapVisualizer(this);
        this.basins = basins;
        placePlants(worldParameters.getStartPlantNumber());
    }

    public void placeBasins(int amount){

    }

    public void placeWater(){

    }

    public boolean isWater(Vector2d position){
        return plants.get(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }
}
