package oop.model;

public class AniamalParameters {
    private int animalStartEnergy;


    private int reproduceEnergy;
    private int eatEnergy;

    public int getEatEnergy() {return eatEnergy;}

    public int getAnimalStartEnergy() {
        return this.animalStartEnergy;
    }

    public int getReproduceEnergy() {
        return this.reproduceEnergy;
    }


    public AniamalParameters(){
        this.animalStartEnergy=10;
        this.reproduceEnergy=5;
        this.eatEnergy=3;
    }

}
