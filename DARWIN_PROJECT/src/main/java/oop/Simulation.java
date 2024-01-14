package oop;

import oop.model.*;

import java.util.Iterator;

public class Simulation implements Runnable{
//    public List<Animal> animals;
    public WorldMap map;
    public AnimalParameters animalParameters;
    public boolean run;


    public Simulation(AbstractWorldMap map, AnimalParameters animalParameters, WorldParameters worldParameters){
        this.animalParameters = animalParameters;
        this.map = map;
        this.run = true;

        RandomAnimalsPositionGenerator randomPositionGenerator = new RandomAnimalsPositionGenerator(worldParameters.getMapWidth(), worldParameters.getMapHeight(), worldParameters.getStartAnimalNumber());
        Iterator<Vector2d> positionsIterator = randomPositionGenerator.iterator();

        while(positionsIterator.hasNext()) {
            Vector2d animalPosition =  positionsIterator.next();
            map.placeAnimal(new Animal(animalPosition, this.animalParameters));
        }
    }


    @Override
    public void run() {
        while(this.run){
            map.dayRoutine();
        }
    }

}
