package oop.model;

import oop.Simulation;

import java.util.List;
import java.util.Map;

public class Statistics implements ChangeStats{
    private Simulation simulation;
    private int animalsNumber;
    private int plantsNumber;
    private int freeFields;
    private Genom mostGenom; // brakuje statystyk dla genomu
    private int averageEnergy;
    private int averageLifeTime;
    private int averageChildren;
    private WorldMap map;

    public Statistics(Simulation simulation){
        this.simulation = simulation;
        this.map = simulation.getMap();
        this.animalsNumber = map.getAllAnimals().size();
        this.plantsNumber = map.getAllPlants().size();
        this.freeFields = countFreeFields();
        this.averageEnergy = checkAverageEnergy();
        this.averageLifeTime = checkAverageLifetime();
        this.averageChildren = checkAverageChildren();

    }
    @Override
    public void statsChanged() { // TO DO

    }

    public int countFreeFields(){
        int freeFields = map.getHeight() * map.getWidth();
        Map<Vector2d, List<Animal>> aliveAnimals =  map.getAliveAnimals();
        Map<Vector2d, Plant> plants = map.getAllPlants();
        for(Vector2d position: aliveAnimals.keySet()) {
            freeFields -= 1;
        }
        for(Vector2d position: plants.keySet()) {
            freeFields -= 1;
        }
        return freeFields;
    }

    public int checkAverageEnergy() {
        int averageEnergy = 0;
        int animalsAmount = 0;
        Map<Vector2d, List<Animal>> aliveAnimals = map.getAliveAnimals();
        for (Vector2d position : aliveAnimals.keySet()) {
            List<Animal> animals = aliveAnimals.get(position);
            for (Animal animal : animals) {
                averageEnergy += animal.getEnergyLevel();
                animalsAmount += 1;
            }
        }
        return averageEnergy / animalsAmount;
    }

    public int checkAverageLifetime(){
        int averageLifetime = 0;
        List<Animal> deceasedAnimals = map.getDeceasedAnimals();
        int animalsAmount = deceasedAnimals.size();
        for(Animal animal : deceasedAnimals){
            averageLifetime += animal.getAge();
        }

        return averageLifetime/animalsAmount;
    }

    public int checkAverageChildren(){
        int averageChildren = 0;
        int animalsAmount = 0;
        Map<Vector2d, List<Animal>> aliveAnimals = map.getAliveAnimals();
        for (Vector2d position : aliveAnimals.keySet()) {
            List<Animal> animals = aliveAnimals.get(position);
            for (Animal animal : animals) {
                averageChildren += animal.howManyChildren();
                animalsAmount += 1;
            }
        }

        return averageChildren/animalsAmount;
    }
}
