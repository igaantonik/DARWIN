package oop.model;
// exceptions do zrobienia
public class WorldParametersBuilder {
    private int mapWidth;
    private int mapHeight;
    private int mapWariant; //0-normal 1-extra
    private int startAnimalNumber;
    private int startPlantNumber;
    private int dailyPlantsAdded;
    private int basinsNumber;

    public WorldParametersBuilder(){}

    public WorldParametersBuilder setBasinsNumber(int basinsNumber) {
        this.basinsNumber = basinsNumber;
        return this;
    }

    public WorldParametersBuilder setDailyPlantsAdded(int dailyPlantsAdded) {
        this.dailyPlantsAdded = dailyPlantsAdded;
        return this;
    }

    public WorldParametersBuilder setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
        return this;
    }

    public WorldParametersBuilder setMapWariant(int mapWariant) {
        if(mapWariant != 0 & mapWariant != 1){
            throw new IllegalArgumentException();
        }
        this.mapWariant = mapWariant;
        return this;
    }

    public WorldParametersBuilder setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
        return this;
    }

    public WorldParametersBuilder setStartAnimalNumber(int startAnimalNumber) {
        this.startAnimalNumber = startAnimalNumber;
        return this;
    }

    public WorldParametersBuilder setStartPlantNumber(int startPlantNumber) {
        this.startPlantNumber = startPlantNumber;
        return this;
    }

    public WorldParameters build(){
        return new WorldParameters(mapHeight,mapWariant,mapWidth,startAnimalNumber, startPlantNumber, dailyPlantsAdded, basinsNumber );
    }
}
