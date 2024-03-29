package oop.model;

public class Plant implements MapElement {
    private final Vector2d position;
//    private int energyToGet;
//    private WorldParameters worldParameters;
    private Vector2d preferenceUpperRight;
    private Vector2d preferenceLowerLeft;

    public Plant(Vector2d position) {
        this.position = position;
//        this.worldParameters = worldParameters;
//        this.energyToGet = parameters.getPlantEnergy();
    }

    @Override
    public Vector2d getPosition() {return position;}

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

//    void gotEaten(){
//        energyToGet = 0;
//    }


    public String toString(){
        return "*";
    }
}
