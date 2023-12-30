package oop.model;

public class AnimalParameters {
    private int animalStartEnergy;
    private int energyToReproduce;
    private int energyLostToReproduce;
    private int eatEnergy;

    private int genomLength;
    private int genomWariant; //0-normal 1-extra
    private int minMutation;
    private int maxMutation;

    public int getEatEnergy() {return this.eatEnergy;}

    public int getAnimalStartEnergy() {
        return this.animalStartEnergy;
    }

    public int getEnergyToReproduce() {
        return this.energyToReproduce;
    }

    public int getEnergyLostToReproduce(){return this.energyLostToReproduce;}

    public int getGenomLength(){return this.genomLength;}

    public int getGenomWariant(){ return this.genomWariant;}

    public int getMinMutation() {
        return this.minMutation;
    }

    public int getMaxMutation() {
        return this.maxMutation;
    }


    //jeszcze nie wiem jak to ustawiac

    public AnimalParameters(){
        this.animalStartEnergy=10;
        this.energyToReproduce =5;
        this.energyLostToReproduce = 2;
        this.eatEnergy=3;
        this.genomLength=20;
        this.genomWariant=1;
        this.maxMutation=10;
        this.minMutation=2;
    }

}
