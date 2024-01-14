package oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected List<Animal> livingAnimals = new ArrayList<>();
    protected Map<Vector2d, List<Animal>> aliveAnimalsMap = new HashMap<>();
    protected List<Animal> deceased_animals = new ArrayList<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected Boundary boundary;
    protected int height;
    protected int width;
    protected WorldParameters worldParameters;
    protected ArrayList<MapChangeListener> listeners = new ArrayList<>();
    protected UUID uuid;


    // listeners
    @Override
    public void addObserver(MapChangeListener observer) {
        this.listeners.add(observer);
    }
    public void deleteObserver(MapChangeListener observer) {
        int i = listeners.indexOf(observer);
        if (i >= 0) {
            listeners.remove(i);
        }
    }
    @Override
    public void mapChanged(String message) {
        for (MapChangeListener observer : listeners) {
            observer.mapChanged(this, message);
        }
    }

    // Day
    public void dayRoutine(int day){
        lookForDeadAnimals();
        moveAllAnimals();
        dinner();
        reproduction();
        dailyPlantGrow();
        mapChanged("");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //Daily events Plant

    public void placePlants(int plantsAmount) {
        RandomPlantPositionGenerator randomPositionGenerator = new RandomPlantPositionGenerator(width - 1, height - 1, plantsAmount, plants);

        for (Vector2d plantPosition : randomPositionGenerator) {
            placePlant(new Plant(plantPosition));
        }
    }


    @Override
    public void dailyPlantGrow(){
        placePlants(worldParameters.getDailyPlantsAdded());
    }


    @Override
    public boolean placePlant(Plant plant){
        Vector2d position = plant.getPosition();
        plants.put(position, plant);
        return true;
    }

    @Override
    public void removePlant(Plant plant){
        Vector2d position = plant.getPosition();
        plants.remove(position);
    }

    // Daily events Animals
    @Override
    public boolean placeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        if(!livingAnimals.contains(animal)){
            livingAnimals.add(animal);
        }
        if (!canMoveTo(position)) {
            return false;
        }
        if(aliveAnimalsMap.containsKey(position)){
            List<Animal> old_list;
            old_list = aliveAnimalsMap.get(position);
            old_list.add(animal);
            aliveAnimalsMap.replace(position, old_list);
        } else aliveAnimalsMap.put(position, new ArrayList<Animal>(List.of(animal)));
//        mapChanged("animal added");
        return true;
    }

    @Override
    public void move(Animal animal) {
        removeAnimal(animal);
        Vector2d position = animal.getPosition();
        animal.move(this, this.width);
        placeAnimal(animal);
    }

    @Override
    public void removeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        if(!aliveAnimalsMap.containsKey(position)){
            return;
        }
        List<Animal> animals = aliveAnimalsMap.get(position);
        if(!animals.isEmpty()){
            animals.remove(animal);
        }
        if(animals.isEmpty()){
            aliveAnimalsMap.remove(position);
        } else {
            aliveAnimalsMap.replace(position, animals);
        }
    }
    @Override
    public void deadAnimal(Animal animal){
        removeAnimal(animal);
        livingAnimals.remove(animal);
        deceased_animals.add(animal);
    }

    @Override
    public void moveAllAnimals(){
        for(Animal animal: livingAnimals){
            this.move(animal);
        }
    }

    @Override
    public void dinner(){
        List<Plant> plantsEaten = new ArrayList<>();
        for(Vector2d position: plants.keySet()){
            if(aliveAnimalsMap.containsKey(position)){
                sortAliveAnimalsInVector(position);
                List<Animal> animalsInVector = aliveAnimalsMap.get(position);
                Animal animal = animalsInVector.get(animalsInVector.size()-1);
                animal.eat();
                plantsEaten.add(plants.get(position));
            }
        }
        for(Plant plant: plantsEaten){
            removePlant(plant);
        }
    }

    @Override
    public void reproduction(){
        for(Vector2d position: aliveAnimalsMap.keySet()){
            sortAliveAnimalsInVector(position);
            List<Animal> animals = aliveAnimalsMap.get(position);
            List<Animal> newAnimals = new ArrayList<>();
            int i=animals.size()-1;
            while(i > 0 && animals.get(i-1).canReproduce()){
                Animal animal1 = animals.get(i);
                Animal animal2 = animals.get(i-1);
                Animal child = animal1.reproduce(animal2, position);
                newAnimals.add(child);
                livingAnimals.add(child);
                i -= 2;
            }
            animals.addAll(newAnimals);
            aliveAnimalsMap.replace(position, animals);
        }
    }

    @Override
    public void lookForDeadAnimals(){
        List<Animal> deceasedAnimals = new ArrayList<>();
        for(Animal animal: livingAnimals){
            if(!animal.isAlive()){
                deceasedAnimals.add(animal);
            }
        }
        for(Animal animal: deceasedAnimals){
            deadAnimal(animal);
        }
    }


    @Override
    public void sortAliveAnimalsInVector(Vector2d position){
        List<Animal> animals = aliveAnimalsMap.get(position);
        if(!animals.isEmpty()){
            Collections.sort(animals);
            aliveAnimalsMap.replace(position, animals);
        }
    }


    // Looking for elements in vector

    @Override
    public boolean isPlant(Vector2d position){
        return plants.get(position) != null;
    }

    @Override
    public boolean isAnimal(Vector2d position){
        return aliveAnimalsMap.get(position) != null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return isPlant(position) || isAnimal(position);
    }

    @Override
    public List animalAt(Vector2d position) {
        return aliveAnimalsMap.get(position);
    }

    @Override
    public MapElement plantAt(Vector2d position) {
        return plants.get(position);
    }

    @Override
    public Map<Vector2d,MapElement> getElements() {
        Map<Vector2d,MapElement> result = new HashMap<>();
        for (Vector2d key : aliveAnimalsMap.keySet()){
            sortAliveAnimalsInVector(key);
            result.put(key, aliveAnimalsMap.get(key).get(0));
        }
        for(Vector2d vector: this.plants.keySet()){
            if(!result.containsKey(vector)) {
                result.put(vector, plantAt(vector));
            }
        }
        return result;
    }


    @Override
    public UUID getId() {
        return uuid;
    }


    public boolean waterAt(Vector2d position){return false;}

    public List<Animal> getAllAnimals(){return this.livingAnimals;}
  
    public Map<Vector2d, List<Animal>> getAliveAnimals(){return this.aliveAnimalsMap;}
  
    public List<Animal> getDeceasedAnimals(){return deceased_animals;}
  
    public Map<Vector2d, Plant> getAllPlants(){return plants;}

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0,0), new Vector2d(this.width, this.height));
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
