package oop.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal implements MapElement, Comparable<Animal>{
    private int age;
    private Vector2d position;
    private MapDirection direction;
    private Genom genom;
    private Energy energy;
    private List<Animal> children;
    private AnimalParameters parameters;
    private boolean isDeceased;
    private int plantsEaten;

    // Creating Animal
    public Animal(Vector2d position, AnimalParameters parameters){
        Random rand = new Random();
        this.genom = ChosingGenomVariant.createGenom(parameters);
        this.age = 0;
        this.children = new ArrayList<>();
        this.position = position;
        this.parameters = parameters;
        this.direction = MapDirection.values()[rand.nextInt(8)];
        this.isDeceased = false;
        this.energy = new Energy(this.parameters.getAnimalStartEnergy(), this.parameters.getEnergyToReproduce());
        this.plantsEaten = 0;
    }

    public Animal(Vector2d position, AnimalParameters parameters, int energylevel, Genom genom){
        this.genom = genom;
        this.age = 0;
        this.children = new ArrayList<>();
        this.position = position;
        this.parameters = parameters;
        this.direction = MapDirection.NORTH;
        this.isDeceased = false;
        this.energy = new Energy(energylevel, this.parameters.getEnergyToReproduce());
        this.plantsEaten = 0;

    }

    // Getting atributs
    public Energy getEnergy(){
        return this.energy;
    }

    public int getEnergyLevel(){
        return this.energy.getEnergyLevel();
    }
    
    public int getAge(){
        return this.age;
    }

    public Genom getGenom() {
        return genom;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public boolean isDeceased(){ return this.isDeceased;}
    public int getPlantsEaten(){ return this.plantsEaten;}


    // Statistics
    public int howManyChildren(){
        return  this.children.size();
    }

    public int howManyOffspring(){
        List<Animal> offspring = getOffspring(new ArrayList<>());
        return offspring.size();
    }
    public List<Animal> getOffspring(List<Animal> offspring){
        for(Animal child: children){
            if(!offspring.contains(child)) {
                offspring.addAll(child.getOffspring(offspring));
            }
        }
        return offspring;
    }


    // Daily events
    public void move(MoveValidator validator, int width){
        MapDirection new_direction = genom.changeDirection(this.direction);
        Vector2d new_position = genom.changePosition(this.position, new_direction);
        Vector2d wrapped_position;
        wrapped_position = new_position.enwrapping(width+1);
        this.direction = new_direction;
        if(validator.canMoveTo(new_position)){
            this.position = wrapped_position;
        }
        this.loseEnergy(1);
        this.age += 1;
    }

    public void eat(){
        this.energy.addEnergy(parameters.getEatEnergy(), parameters.getAnimalStartEnergy());
        this.plantsEaten +=1;
    }

    public boolean isAlive(){
        if(this.energy.canLive()){
            return true;
        }
        else{
            this.isDeceased = true;
            return false;
        }
    }

    public void addChild(Animal child){
        this.children.add(child);
    }

    public boolean canReproduce(){
        return this.energy.enoughToReproduce();
    }

    public Animal reproduce(Animal animal, Vector2d position){
        Genom newGenom = ChosingGenomVariant.createChildGenom(parameters, this, animal);
        newGenom.mutation();
        Animal child = new Animal(position, parameters, parameters.getEnergyLostToReproduce()*2,newGenom);

        this.addChild(child);
        this.loseEnergy(parameters.getEnergyLostToReproduce());

        animal.addChild(child);
        animal.loseEnergy(parameters.getEnergyLostToReproduce());
        return child;
    }

    public void loseEnergy(int energylevel){
        this.energy.lostEnergy(energylevel);
    }


    // Other
    public String toString(){
        return this.energy.toString();
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

        int cmp_childs = Integer.compare(this.howManyChildren(), animal.howManyChildren());
        return cmp_childs;
    }
}
