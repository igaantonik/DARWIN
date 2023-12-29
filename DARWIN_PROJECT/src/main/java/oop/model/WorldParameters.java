package oop.model;

public class WorldParameters {
    private int mapWidth;
    private int mapHeight;
    private int mapWariant; //0-normal 1-extra
    private int startAnimalNumber;
    private int genomLength;
    private int genomWariant; //0-normal 1-extra
    private int minMutation;
    private int maxMutation;
    private int startPlantNumber;
    private int dailyPlantsAdded;

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {return mapHeight;}

    public int getMapWariant() {
        return mapWariant;
    }

    public int getStartAnimalNumber() {return startAnimalNumber;}

    public int getGenomLength(){return genomLength;}

    public int getGenomWariant(){ return genomWariant;}

    public int getMinMutation() {
        return minMutation;
    }

    public int getMaxMutation() {
        return maxMutation;
    }

    public int getDailyPlantsAdded() {
        return dailyPlantsAdded;
    }

    public int getStartPlantNumber() {
        return startPlantNumber;
    }

}
