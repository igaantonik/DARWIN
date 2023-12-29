package oop.model;

public class AniamalParameters {
    private int animalStartEnergy;
    private int energyToReproduce;
    private int energyLostToReproduce;
    private int eatEnergy;

    public int getEatEnergy() {return eatEnergy;}

    public int getAnimalStartEnergy() {
        return this.animalStartEnergy;
    }

    public int getEnergyToReproduce() {
        return this.energyToReproduce;
    }

    public int getEnergyLostToReproduce(){return this.energyLostToReproduce;}


    public AniamalParameters(){
        this.animalStartEnergy=10;
        this.energyToReproduce =5;
        this.energyLostToReproduce = 2;
        this.eatEnergy=3;
    }

}
