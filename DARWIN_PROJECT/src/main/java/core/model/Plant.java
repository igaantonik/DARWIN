package core.model;

public class Plant implements MapElement {
    private final Vector2d position;
    private int energyToGet;
    private Vector2d preferenceUpperRight;
    private Vector2d preferenceLowerLeft;

    public Plant(Vector2d position,int energyToGet) {
        this.position = position;
        this.energyToGet = energyToGet;
    }

    @Override
    public Vector2d getPosition() {return position;}

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    void gotEaten(){
        energyToGet = 0;
    }
}
