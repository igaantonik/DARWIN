package oop.model;


import java.util.Random;

public class Animal implements MapElement, Comparable<Animal>{
    private int childsNum;
    private int age;
    private Vector2d position;
    private MapDirection direction;
    private Genom genom;
    private Energy energy;
    private AniamalParameters parameters;
    private boolean isDeceased;


    public Animal(Vector2d position, AniamalParameters parameters){
        Random rand = new Random();
        this.genom = new GenomBackAndForth();
        this.age = 0;
        this.childsNum = 0;
        this.position = position;
        this.parameters = parameters;
        this.direction = MapDirection.values()[rand.nextInt(8)];
        this.isDeceased = false;
        this.energy = new Energy(this.parameters.getAnimalStartEnergy(), this.parameters.getReproduceEnergy());
    }

    public Animal(Vector2d position, AniamalParameters parameters, int energylevel, Genom genom){
        this.genom = genom;
        this.age = 0;
        this.childsNum = 0;
        this.position = position;
        this.parameters = parameters;
        this.direction = MapDirection.NORTH;
        this.isDeceased = false;
        this.energy = new Energy(energylevel, this.parameters.getReproduceEnergy());
    }
    public Energy getEnergy(){
        return this.energy;
    }

    public int getEnergyLevel(){
        return this.energy.getEnergyLevel();
    }
    
    public int getAge(){
        return this.age;
    }
    
    public int getChildsNum(){
        return  this.childsNum;
    }

    public Genom getGenom() {
        return genom;
    }

    public void move(MoveValidator validator, int width){
        MapDirection new_direction = genom.changeDirection(this.direction);
        Vector2d new_position = genom.changePosition(this.position, new_direction);
        new_position = new_position.enwrapping(width);
        this.direction = new_direction;
        if(validator.canMoveTo(new_position)){
            this.position = new_position;
        }
        this.energy.lostEnergy(1);
        this.age += 1;
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
    public boolean canReproduce(){
        return this.energy.enoughToReproduce();
    }
    public Animal reproduce(Animal animal, Vector2d position){
        //drzewko rodzinne
        Genom newGenom = new GenomBackAndForth(this, animal);
        newGenom.mutation();
        return new Animal(position, parameters, parameters.getReproduceEnergy()*2,newGenom);
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
