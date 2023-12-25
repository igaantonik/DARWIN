package oop.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, List<Animal>> aliveAnimals = new HashMap<>();
    protected List<Animal> deceased_animals = new ArrayList<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected int plantsAmount;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected ArrayList<MapChangeListener> listeners = new ArrayList<>();
    protected UUID uuid;

    // Popraiwnienie mapvisualizera
    // Sprawdzanie czy na polu jest woda ( tylko dla mapy FlowsandEbbs )
    // trzeba trzymac parametry i dodawać roslinki kiedy jest ich mniej niz okresolono

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

    // usunełam error z zajetą pozycja

    @Override
    public boolean placeAnimal(Animal animal){
        Vector2d position = animal.getPosition();
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
        Vector2d position = animal.getPosition();
        animal.move(this);
        Vector2d new_position = animal.getPosition();
        if (!position.equals(new_position)) {
            placeAnimal(animal);
        }
    }

    @Override
    public void deadAnimal(Animal animal){
        Vector2d position = animal.getPosition();
        List<Animal> animals = aliveAnimals.get(position);
        animals.remove(animal);
        aliveAnimals.replace(position,animals);
        deceased_animals.add(animal);
    }

    public void sortAliveAnimalsInVector(Vector2d position){
        List<Animal> animals = aliveAnimals.get(position);
        Collections.sort(animals);
    }


    // nadpisanie funkcji isoccupied dla flowsandebbs

    public boolean isPlant(Vector2d position){
        return plants.get(position) != null;
    }

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


    @Override
    public MapElement plantAt(Vector2d position) {
        return plants.get(position);
    }

    //chyba trzeba tworzyc roslinlke??
    public boolean placePlant(Plant plant) throws PositionAlreadyOccupiedException {
        Vector2d position = plant.getPosition();
        if (this.plantAt(position) == null) {
            throw new PositionAlreadyOccupiedException(position);
        }

        plants.put(position, plant);
        mapChanged("plant added");
        return true;
    }

    public void removePlant(Plant plant){
        Vector2d position = plant.getPosition();
        if(plants.containsValue(plant)){
            plants.remove(position);
            mapChanged("plant deleted");
        }
    }


}