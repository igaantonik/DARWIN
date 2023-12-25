package oop.model;


import java.util.ArrayList;
import java.util.Arrays;

public class Animal implements MapElement, Comparable<Animal>{
    private final int childsNum;
    private int age;
    private int childs_num;
    private Vector2d position;
    private MapDirection direction;
    private Genom genom;
    private Energy energy;
    private AniamalParameters parameters;
    private boolean isDeceased;

    //stracona energia == energia mlodego
    // age cmp
    // childs num cmp
    // zwierzak umarl
    //

    public Animal(Vector2d position, AniamalParameters parameters){
        this.genom = new GenomBackAndForth(new ArrayList<>(Arrays.asList(0,1,0,0,1,0)));
        this.age = 0;
        this.childsNum = 0;
        this.position = position;
        this.parameters = parameters;
        this.direction = MapDirection.NORTH;
        this.isDeceased = false;
        this.energy = new Energy(this.parameters.getAnimalStartEnergy(), this.parameters.getReproduceEnergy());

    }
    public Energy getEnergy(){
        return this.energy;
    }
    
    public int getAge(){
        return this.age;
    }
    
    public int getChildsNum(){
        return  this.childsNum;
    }


    public void move(MoveValidator validator){
        MapDirection new_direction = genom.changeDirection(this.direction);
        Vector2d new_position = genom.changePosition(this.position, new_direction);
        this.direction = new_direction;
        if(validator.canMoveTo(new_position)){
            this.position = new_position;
        }
        this.energy.lostEnergy(1);
    }
    public void eat(){
        this.energy.addEnergy(parameters.getEatEnergy());
    }
    public boolean isAlive(){
        if(energy.canLive()){
            return true;
        }
        else{
            this.isDeceased = true;
            return false;
        }
    }


    public Vector2d getPosition() {
        return this.position;
    }
    public String toString(){
        return this.direction.toString();
    }
    @Override
    public int compareTo(Animal animal) {
        int cmp_energy = Integer.compare(this.getEnergy().getEnergyLevel(), animal.getEnergy().getEnergyLevel());
        if (cmp_energy != 0) {
            return cmp_energy;
        }

        int cmp_age = Integer.compare(this.getAge(), animal.getAge());
        if (cmp_age != 0) {
            return cmp_age;
        }

        int cmp_childs = Integer.compare(this.getChildsNum(), animal.getChildsNum());
        return cmp_childs;
    }
}
