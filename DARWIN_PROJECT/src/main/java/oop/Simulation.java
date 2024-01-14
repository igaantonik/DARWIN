package oop;

import oop.model.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Simulation implements Runnable{
//    public List<Animal> animals;
    public WorldMap map;
    public AnimalParameters animalParameters;
    public int simulationDay;


    public Simulation(AbstractWorldMap map, AnimalParameters animalParameters, WorldParameters worldParameters){
        this.animalParameters = animalParameters;
        this.map = map;
        this.simulationDay = 50;

        RandomAnimalsPositionGenerator randomPositionGenerator = new RandomAnimalsPositionGenerator(worldParameters.getMapWidth(), worldParameters.getMapHeight(), worldParameters.getStartAnimalNumber());
        Iterator<Vector2d> positionsIterator = randomPositionGenerator.iterator();

        while(positionsIterator.hasNext()) {
            Vector2d animalPosition =  positionsIterator.next();
            map.placeAnimal(new Animal(animalPosition, this.animalParameters));
        }
    }


    @Override
    public void run() {
        for(int d=0; d<this.simulationDay; d++){
            map.dayRoutine(d);
        }
    }

    public WorldMap getMap() {
        return this.map;
    }
}
