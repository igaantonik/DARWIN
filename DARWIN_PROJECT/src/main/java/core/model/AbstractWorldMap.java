package core.model;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected int plantsAmount;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected ArrayList<MapChangeListener> listeners = new ArrayList<>();
    protected UUID uuid;

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


    @Override
    public boolean placeAnimal(Animal animal) throws PositionAlreadyOccupiedException {
        Vector2d position = animal.getPosition();
        if (!canMoveTo(position)) {
            throw new PositionAlreadyOccupiedException(position);
        }

        animals.put(position, animal);
//        mapChanged("animal added");
        return true;
    }


    @Override
    public void move(Animal animal) {
        if(animals.containsValue(animal)){
            Vector2d position = animal.getPosition();
            animal.move(this);
            Vector2d new_position = animal.getPosition();
            if (!position.equals(new_position)) {
                this.animals.remove(position);
                this.animals.put(new_position, animal);
            }
            mapChanged("animal moved");
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.get(position) != null;
    }

    @Override
    public MapElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public Map<Vector2d,MapElement> getElements() {
        Map<Vector2d,MapElement> result = new HashMap<>();
        for (Vector2d key : animals.keySet()){
            result.put(key,animals.get(key));
        }
        return result;
    }


    @Override
    public UUID getId() {
        return uuid;
    }


    public MapElement plantAt(Vector2d position) {
        return plants.get(position);
    }

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
