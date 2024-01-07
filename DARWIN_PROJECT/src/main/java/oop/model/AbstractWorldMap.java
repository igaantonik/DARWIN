package oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected List<Animal> allAnimals = new ArrayList<>();
    protected Map<Vector2d, List<Animal>> aliveAnimals = new HashMap<>();
    protected List<Animal> deceased_animals = new ArrayList<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected int height;
    protected int width;
    protected WorldParameters worldParameters;
    protected ArrayList<MapChangeListener> listeners = new ArrayList<>();
    protected UUID uuid;

    // Popraiwnienie mapvisualizera
    // Sprawdzanie czy na polu jest woda ( tylko dla mapy FlowsandEbbs )
    // trzeba trzymac parametry i dodawać roslinki kiedy jest ich mniej niz okresolono


    // listeners
    public void addObserver(MapChangeListener observer) {
        this.listeners.add(observer);
    }
    public void deleteObserver(MapChangeListener observer) {
        int i = listeners.indexOf(observer);
        if (i >= 0) {
            listeners.remove(i);
        }
    }
    private void mapChanged(String message) {
        for (MapChangeListener observer : listeners) {
            observer.mapChanged(this, message);
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
//    @Override
//    public boolean placePlant(Plant plant) throws PositionAlreadyOccupiedException {
//        Vector2d position = plant.getPosition();
//        if (this.plantAt(position) == null) {
//            throw new PositionAlreadyOccupiedException(position);
//        }
//
//        plants.put(position, plant);
//        mapChanged("plant added");
//        return true;
//    }

    @Override
    public boolean placePlant(Plant plant){
        Vector2d position = plant.getPosition();

        plants.put(position, plant);
        mapChanged("plant added");
        return true;
    }

    @Override
    public void removePlant(Plant plant){
        Vector2d position = plant.getPosition();
        plants.remove(position);
//        if(plants.containsValue(plant)){
//            plants.remove(position);
//            mapChanged("plant deleted");
//        }
    }

    // Daily events Animals
    @Override
    public boolean placeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        if(!allAnimals.contains(animal)){
            allAnimals.add(animal);
        }
        if (!canMoveTo(position)) {
            return false;
        }
        if(aliveAnimals.containsKey(position)){
            List<Animal> old_list;
            old_list = aliveAnimals.get(position);
            old_list.add(animal);
            aliveAnimals.replace(position, old_list);
        } else aliveAnimals.put(position, new ArrayList<Animal>(List.of(animal)));
//        mapChanged("animal added");
        return true;
    }

    @Override
    public void move(Animal animal) {
        removeAnimal(animal);
        Vector2d position = animal.getPosition();
        animal.move(this, this.width);
        placeAnimal(animal);
        mapChanged("mapa sie zmieniła");
    }

    @Override
    public void removeAnimal(Animal animal){
//        allAnimals.remove(animal);
        Vector2d position = animal.getPosition();
        List<Animal> animals = aliveAnimals.get(position);
        animals.remove(animal);
        if(animals.isEmpty()){
            aliveAnimals.remove(position);
        } else {
            aliveAnimals.replace(position, animals);
        }
    }
    @Override
    public void deadAnimal(Animal animal){
        removeAnimal(animal);
        allAnimals.remove(animal);
        deceased_animals.add(animal);
    }

    @Override
    public void moveAllAnimals(){
        for(Animal animal:allAnimals){
            this.move(animal);
        }
    }

    @Override
    public void dinner(){
        for(Vector2d position: plants.keySet()){
            if(aliveAnimals.containsKey(position)){
                sortAliveAnimalsInVector(position);
                List<Animal> animalsInVector = aliveAnimals.get(position);
                Animal animal = animalsInVector.get(animalsInVector.size()-1);
                animal.eat();
                removePlant(plants.get(position));
            }
        }
    }

    @Override
    public void reproduction(){
        for(Vector2d position: aliveAnimals.keySet()){
            sortAliveAnimalsInVector(position);
            List<Animal> animals = aliveAnimals.get(position);
            List<Animal> newAnimals = new ArrayList<>();
            int i=0;
            while(i + 1 < animals.size() && animals.get(i+1).canReproduce()){
                Animal animal1 = animals.get(i);
                Animal animal2 = animals.get(i+1);
                Animal child = animal1.reproduce(animal2, position);
                newAnimals.add(child);
                allAnimals.add(child);
            }
            animals.addAll(newAnimals);
            aliveAnimals.replace(position, animals);
        }
    }

    @Override
    public void lookForDeadAnimals(){
        List<Animal> deceasedAnimals = new ArrayList<>();
//        for(Vector2d position: aliveAnimals.keySet()) {
//            sortAliveAnimalsInVector(position);
//            List<Animal> animals = aliveAnimals.get(position);
//            int index = animals.size()-1;
//            while( index >= 0 && !animals.get(index).isAlive()){
////                deadAnimal(animals.get(index));
//                index -=1;
//            }
//        }
        for(Animal animal: allAnimals){
            if(!animal.isAlive()){
                deceasedAnimals.add(animal);
            }
        }
        for(Animal animal: deceasedAnimals){
            deadAnimal(animal);
        }
    }

    //posortowane rosnąco mozna uzyc i przy umieraniu zwierzakow jak i przy reprodukcji i zjadaniu roslin

    @Override
    public void sortAliveAnimalsInVector(Vector2d position){
        List<Animal> animals = aliveAnimals.get(position);
        if(!animals.isEmpty()){
            Collections.sort(animals);
            aliveAnimals.replace(position, animals);
        }
    }


    // nadpisanie funkcji isoccupied dla flowsandebbs


    // Looking for elements in vector

    @Override
    public boolean isPlant(Vector2d position){
        return plants.get(position) != null;
    }

    @Override
    public boolean isAnimal(Vector2d position){
        return aliveAnimals.get(position) != null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return isPlant(position) || isAnimal(position);
    }

    @Override
    public List animalAt(Vector2d position) {
        return aliveAnimals.get(position);
    }

    @Override
    public MapElement plantAt(Vector2d position) {
        return plants.get(position);
    }

    @Override
    public Map<Vector2d,MapElement> getElements() {
        Map<Vector2d,MapElement> result = new HashMap<>();
        for (Vector2d key : aliveAnimals.keySet()){
            for(Animal animal: aliveAnimals.get(key)){
                result.put(key, animal);
            }
        }
        return result;
    }


    @Override
    public UUID getId() {
        return uuid;
    }







}
