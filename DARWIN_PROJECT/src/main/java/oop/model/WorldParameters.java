package oop.model;

public class WorldParameters {
    private int mapWidth;
    private int mapHeight;
    private int mapWariant; //0-normal 1-extra
    private int startAnimalNumber;
    private int startPlantNumber;
    private int dailyPlantsAdded;
    private int basinsNumber;
    private int plantEnergy;


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
    public int getPlantEnergy() {return plantEnergy;}
    public int getBasinsNumber() {
        return basinsNumber;
    }

    // jeszcze nie wiem jak to ustawiac

    public WorldParameters(){
        this.mapWidth = 10;
        this.mapHeight = 10;
        this.mapWariant = 0; //0-normal 1-extra
        this.startAnimalNumber = 1;
        this.startPlantNumber = 3;
        this.dailyPlantsAdded = 2;
        this.plantEnergy = 5;
        this.basinsNumber = 0;
    }
}
