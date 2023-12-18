package core.model;

public class Parameters {
    private int animalStartEnergy;

    private int animalDailyLostEnergy;

    private int reproduceEnergy;

    public int getAnimalStartEnergy() {
        return this.animalStartEnergy;
    }

    public int getReproduceEnergy() {
        return this.reproduceEnergy;
    }

    public int getAnimalDailyLostEnergy(){return this.animalDailyLostEnergy; }

    public Parameters(){
        this.animalStartEnergy=10;
        this.reproduceEnergy=5;
    }

}
