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

    public AnimalParameters(int animalStartEnergy, int energyToReproduce, int energyLostToReproduce, int eatEnergy, int genomLength, int genomWariant, int maxMutation, int minMutation){
        this.animalStartEnergy= animalStartEnergy;
        this.energyToReproduce = energyToReproduce;
        this.energyLostToReproduce = energyLostToReproduce;
        this.eatEnergy = eatEnergy;
        this.genomLength = genomLength;
        this.genomWariant = genomWariant;
        this.maxMutation = maxMutation;
        this.minMutation = minMutation;
    }



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


}
