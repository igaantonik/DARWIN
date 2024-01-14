package oop.model;

import oop.MapVisualizer;

import java.util.*;

public class FlowsAndEbbs extends AbstractWorldMap implements WorldMap{
    private List<WaterBasin> basins = new ArrayList<>();
    private int basinsNumber;
    private MapVisualizer mapVisualizer;
    public FlowsAndEbbs(WorldParameters worldParameters) {
        this.uuid = UUID.randomUUID();
        this.height = worldParameters.getMapHeight();
        this.width = worldParameters.getMapWidth();
        this.worldParameters = worldParameters;
        this.mapVisualizer = new MapVisualizer(this);
        this.basinsNumber = worldParameters.getBasinsNumber();
        //this.basins = basins;
        placePlants(worldParameters.getStartPlantNumber());
        placeBasins(this.basinsNumber);
    }

    public void placeBasins(int amount) {
        RandomWaterBasinPositionGenerator basinCells = new RandomWaterBasinPositionGenerator(width - 1, height - 1, amount);
        int cellWidth = basinCells.getCellWidth();
        int cellHeight = basinCells.getCellHeight();
        for (Vector2d basinCell : basinCells) {
            RandomLLURPositionGenerator basinLLUR = new RandomLLURPositionGenerator(basinCell,cellWidth,cellHeight);
            Vector2d lowerLeft = null;
            Vector2d upperRight = null;
            for (Vector2d position : basinLLUR) {
                if (lowerLeft == null) {
                    lowerLeft = position;
                } else {
                    upperRight = position;
                    break;
                }
            }
            if (lowerLeft != null && upperRight != null && lowerLeft.follows(upperRight)) {
                Vector2d pom = lowerLeft;
                lowerLeft = upperRight;
                upperRight = pom;
                basins.add(new WaterBasin(upperRight,lowerLeft));
            }


        }
    }


    @Override
    public void dayRoutine(int day){
        lookForDeadAnimals();
        moveAllAnimals();
        dinner();
        reproduction();
        dailyPlantGrow();
        allBasinsMove();
        if(day%4==3){
            changeFlow();
        }
        mapChanged("mapa sie zmieniła");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //zakładam ze wszystkie zbiorniki maja przyplywy i odplywy w tym samym kierunku
    public void changeFlow(){
        for(WaterBasin basin: basins){
            basin.changeFlow();
        }
    }

    public void allBasinsMove(){
        for(WaterBasin basin: basins){
            basin.changeBoundaries();
            basin.changeFlowDirection(basin.getFlowDirection().next().next());
        }
    }


    @Override
    public boolean waterAt(Vector2d position){
        for (WaterBasin basin: basins){
            if (position.precedes(basin.getUpperRight()) && position.follows(basin.getLowerLeft())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.getY() >= 0 && position.getY() <= height - 1;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return isPlant(position) || isAnimal(position) || waterAt(position);
    }

    @Override
    public Map<Vector2d,MapElement> getElements() {
        Map<Vector2d,MapElement> result = new HashMap<>();
        for (Vector2d key : aliveAnimalsMap.keySet()){
            sortAliveAnimalsInVector(key);
            result.put(key, aliveAnimalsMap.get(key).get(0));
        }
        for(Vector2d vector: this.plants.keySet()){
            if(!result.containsKey(vector)) {
                result.put(vector, plantAt(vector));
            }
        }
        for (WaterBasin basin: basins){
            for(int i = basin.getLowerLeft().getX(); i<= basin.getUpperRight().getX(); i++){
                for(int j = basin.getLowerLeft().getY(); j<basin.getUpperRight().getY();j++){
                    Water water = new Water(new Vector2d(i,j));
                    result.put(new Vector2d(i,j),water);
                }

            }
        }
        return result;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(width,height));
    }
}
