package oop.model;

public class WorldParameters {
    private int mapWidth;
    private int mapHeight;
    private int mapWariant; //0-normal 1-extra
    private int startAnimalNumber;
    private int startPlantNumber;
    private int dailyPlantsAdded;
    private int basinsNumber;
//    private int plantEnergy;


    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {return mapHeight;}

    public int getMapWariant() {
        return mapWariant;
    }

    public int getStartAnimalNumber() {return startAnimalNumber;}

    public int getDailyPlantsAdded() {
        return dailyPlantsAdded;
    }

    public int getStartPlantNumber() {
        return startPlantNumber;
    }
//    public int getPlantEnergy() {return plantEnergy;}
    public int getBasinsNumber() {
        return basinsNumber;
    }

    // jeszcze nie wiem jak to ustawiac

    public WorldParameters(int mapHeight, int mapWariant, int mapWidth, int startAnimalNumber, int startPlantNumber, int dailyPlantsAdded, int basinsNumber ){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.mapWariant = mapWariant; //0-normal 1-extra
        this.startAnimalNumber = startAnimalNumber;
        this.startPlantNumber = startPlantNumber;
        this.dailyPlantsAdded = dailyPlantsAdded;
        this.basinsNumber = basinsNumber;
    }
}
