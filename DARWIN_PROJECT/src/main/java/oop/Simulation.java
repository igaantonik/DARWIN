package oop;

import oop.model.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Simulation {
//    public List<Animal> animals;
    public WorldMap map;
    public AnimalParameters animalParameters;
    public WorldParameters worldParameters;


    public Simulation(){
        this.worldParameters = new WorldParametersBuilder()
                .setStartAnimalNumber(10)
                .setMapHeight(10)
                .setMapWariant(1)
                .setDailyPlantsAdded(2)
                .setMapWidth(10)
                .setStartPlantNumber(5)
                .build();
        this.map = ChosingMapVariant.createMap(worldParameters);

        MapChangeListener observer = new ConsoleMapDisplay();
        map.addObserver(observer);

        this.animalParameters = new AnimalParametersBuilder()
                .setGenomWariant(1)
                .setEatEnergy(5)
                .setAnimalStartEnergy(10)
                .setEnergyToReproduce(5)
                .setEnergyLostToReproduce(3)
                .setGenomLength(20)
                .setMinMutation(5)
                .setMaxMutation(15)
                .build();

        RandomAnimalsPositionGenerator randomPositionGenerator = new RandomAnimalsPositionGenerator(worldParameters.getMapWidth(), worldParameters.getMapHeight(), worldParameters.getStartAnimalNumber());
        Iterator<Vector2d> positionsIterator = randomPositionGenerator.iterator();

        while(positionsIterator.hasNext()) {
            Vector2d animalPosition =  positionsIterator.next();
            map.placeAnimal(new Animal(animalPosition, this.animalParameters));
        }
    }

    public void run(int days){
        for(int d=0; d<days; d++){
            map.dayRoutine();
        }
    }
    public WorldMap getMap(){
        return map;
    }


}
