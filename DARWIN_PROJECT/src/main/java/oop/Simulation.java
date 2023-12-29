package oop;

import oop.model.*;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    public List<Animal> animals;
    public WorldMap map;
    public AnimalParameters parameters;

    public Simulation(List<Vector2d> start_positions, WorldMap map){
        this.map = map;
        this.parameters = new AnimalParameters();
        List<Animal> animals = new ArrayList<>();
        for(Vector2d start_position: start_positions) {
            animals.add(new Animal(start_position, this.parameters));
            try {
                map.placeAnimal(animals.get(animals.size() - 1));
            } catch(PositionAlreadyOccupiedException e){
                System.out.println(e.getMessage());
            }
        }
        this.animals = animals;
    }

    public void run(int days){
        for(int d=0; d<days; d++){
            dayRoutine();
        }
    }

    public void dayRoutine(){
        map.lookForDeadAnimals();
        map.moveAllAnimals();
        map.dinner();
        map.reproduction();
        map.dailyPlantGrow();
    }
}
