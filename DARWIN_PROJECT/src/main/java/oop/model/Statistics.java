package oop.model;

import oop.Simulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistics implements ChangeStats{
    private int fields;
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
        this.fields = map.getHeight() * map.getWidth();
    }
    @Override
    public void statsChanged() { // TO DO
        this.animalsNumber = map.getAllAnimals().size();
        this.plantsNumber = map.getAllPlants().size();
        this.freeFields = countFreeFields();
        this.averageEnergy = checkAverageEnergy();
        this.averageLifeTime = checkAverageLifetime();
        this.averageChildren = checkAverageChildren();
        this.mostGenom = checkMostPopularGenom();
    }

    public int countFreeFields(){
        int fieldsfree = this.fields;
        Map<Vector2d, List<Animal>> aliveAnimals =  map.getAliveAnimals();
        Map<Vector2d, Plant> plants = map.getAllPlants();
        for(Vector2d position: aliveAnimals.keySet()) {
            fieldsfree -= 1;
        }
        for(Vector2d position: plants.keySet()) {
            fieldsfree -= 1;
        }
        return fieldsfree;
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
        int averagelifetime = 0;
        List<Animal> deceasedAnimals = map.getDeceasedAnimals();
        int animalsAmount = deceasedAnimals.size();
        for(Animal animal : deceasedAnimals){
            averagelifetime += animal.getAge();
        }
        if(deceasedAnimals.isEmpty()){
            return 0;
        }
        return averagelifetime/animalsAmount;
    }

    public int checkAverageChildren(){
        int averageChildren = 0;
        int animalsAmount = 0;
        Map<Vector2d, List<Animal>> aliveAnimals = map.getAliveAnimals();
        if(aliveAnimals.isEmpty()){
            return 0;
        }
        for (Vector2d position : aliveAnimals.keySet()) {
            List<Animal> animals = aliveAnimals.get(position);
            for (Animal animal : animals) {
                averageChildren += animal.howManyChildren();
                animalsAmount += 1;
            }
        }
        return averageChildren/animalsAmount;
    }


    public Genom checkMostPopularGenom(){
        HashMap<Genom, Integer> genoms = new HashMap<>();
        for(Animal animal:this.map.getAllAnimals()){
            if (genoms.containsKey(animal.getGenom())){
                genoms.replace(animal.getGenom(), genoms.get(animal.getGenom())+1);
            } else{
                genoms.put(animal.getGenom(), 1);
                this.mostGenom = animal.getGenom();
            }
        }
        for(Genom genom: genoms.keySet()){
            if(genoms.get(genom) > genoms.get(this.mostGenom)){
                this.mostGenom = genom;
            }
        }
        return this.mostGenom;
    }

    public String getAnimalsNumber() {return Integer.toString(this.animalsNumber);}

    public String getMostGenom() {
        return mostGenom.getGenes().stream().map(Gen::toString)
                .collect(Collectors.joining(", "));
    }

    public String getAverageChildren() {return Integer.toString(this.averageChildren);}

    public String getAverageEnergy() {return Integer.toString(this.averageEnergy);}

    public String getAverageLifeTime() {return Integer.toString(this.averageLifeTime);}

    public String getFreeFields() {return Integer.toString(this.freeFields);}

    public String getPlantsNumber() {return Integer.toString(this.plantsNumber);}

}
