package oop.model;

public class AnimalParametersBuilder {
    private int animalStartEnergy;
    private int energyToReproduce;
    private int energyLostToReproduce;
    private int eatEnergy;

    private int genomLength;
    private int genomWariant; //0-normal 1-extra
    private int minMutation;
    private int maxMutation;

    public AnimalParametersBuilder(){}

    public AnimalParametersBuilder setEatEnergy(int eatEnergy){
        if(eatEnergy <= 0){
            throw new IllegalArgumentException();
        } else{
            this.eatEnergy = eatEnergy;
        }
        return this;
    }

    public AnimalParametersBuilder setAnimalStartEnergy(int animalStartEnergy) {
        if(animalStartEnergy <= 0){
            throw new IllegalArgumentException();
        }
        this.animalStartEnergy = animalStartEnergy;
        return this;
    }

    public AnimalParametersBuilder setEnergyToReproduce(int energyToReproduce) {
        if(energyToReproduce <= 0){
            throw new IllegalArgumentException();
        }
        this.energyToReproduce = energyToReproduce;
        return this;
    }

    public AnimalParametersBuilder setEnergyLostToReproduce(int energyLostToReproduce) {
        if(energyLostToReproduce <= 0){
            throw new IllegalArgumentException();
        }
        this.energyLostToReproduce = energyLostToReproduce;
        return this;
    }

    public AnimalParametersBuilder setGenomLength(int genomLength) {
        if(genomLength <= 0){
            throw new IllegalArgumentException("Długość Genomu nie może być ujemna");
        }
        this.genomLength = genomLength;
        return this;
    }

    public AnimalParametersBuilder setGenomWariant(int genomWariant) {
        if(genomWariant != 0 & genomWariant != 1){
            throw new IllegalArgumentException();
        }
        this.genomWariant = genomWariant;
        return this;
    }

    public AnimalParametersBuilder setMinMutation(int minMutation) {
        if(minMutation < 0 || minMutation > this.genomLength){
            throw new IllegalArgumentException("Zła minimalna ilość mutacji");
        }
        this.minMutation = minMutation;
        return this;
    }

    public AnimalParametersBuilder setMaxMutation(int maxMutation) {
        if(maxMutation < 0 || maxMutation < minMutation || maxMutation > this.genomLength){
            throw new IllegalArgumentException("Zła maksymalna ilość mutacji");
        }
        this.maxMutation = maxMutation;
        return this;
    }

    public AnimalParameters build(){
        return new AnimalParameters(animalStartEnergy,energyToReproduce,energyLostToReproduce,eatEnergy,genomLength,genomWariant,maxMutation,minMutation);
    }
}
