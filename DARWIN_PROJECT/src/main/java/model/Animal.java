package model;

public class Animal implements MapElement{
    private Vector2d position = new Vector2d(2,2);
    private MapDirection orientation = MapDirection.NORTH;

    static Vector2d vector_min = new Vector2d(0,0);
    static Vector2d vector_max = new Vector2d(4,4);

    public Animal(Vector2d position){
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public Animal(){}

    public Vector2d getPosition() {
        return position;
    }

    public String toString(){
        return this.orientation.toString();
    }

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(){

    }
}
