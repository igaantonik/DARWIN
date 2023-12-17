package model;

public class Water implements MapElement {
    private Vector2d position;
    private WaterBasin waterBasin;

    public Water(Vector2d position) {
        this.position = position;
    }
    @Override
    public Vector2d getPosition(){ return position;}

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    // czy nie przenieść tej metody do Animal?
    public void animalDrowned(Animal animal){
        //animal.drowned();
    }
}
