package core.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Animal implements MapElement{
    private Vector2d position;
    private MapDirection direction;
    private Genom genom;
    private Energy energy;
    private Parameters parameters;


    public Animal(Vector2d position, Parameters parameters){
        this.genom = new GenomBackAndForth(new ArrayList<>(Arrays.asList(1,0,2,3,6,7,0,0,3,4)));
        this.position = position;
        this.parameters = parameters;
        this.direction = MapDirection.NORTH;
        this.energy = new Energy(this.parameters.getAnimalStartEnergy(), this.parameters.getReproduceEnergy());

    }


    public Vector2d getPosition() {
        return this.position;
    }

    public String toString(){
        return this.direction.toString() ;
    }

    boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveValidator validator){

        MapDirection new_direction = genom.changeDirection(this.direction);
        Vector2d new_position = genom.changePosition(this.position, new_direction);
        this.direction = new_direction;
        if(validator.canMoveTo(new_position)){
            this.position = new_position;
        }
        this.energy.lostEnergy(parameters.getAnimalDailyLostEnergy());
    }
}
