package oop.model;

public class Energy {
    int energyLevel;
    int leveltoReproduce;

    // construktor

    public Energy(int energylevel, int leveltoreproduce){
        this.energyLevel = energylevel;
        this.leveltoReproduce = leveltoreproduce;
    }

    // Getting atributes
    public int getEnergyLevel() {
        return this.energyLevel;
    }


    // Daily events
    public boolean enoughToReproduce(){
        if(this.energyLevel >= this.leveltoReproduce){
            return true;
        }
        return false;
    }

    public boolean canLive(){
        if(this.energyLevel <= 0){
            return false;
        }
        return true;
    }

    public void addEnergy(int addedenergy, int maxEnergy){
        this.energyLevel += addedenergy;
        if(maxEnergy < this.energyLevel){
            this.energyLevel = maxEnergy;
        }
    }

    public void lostEnergy(int lostenergy){
        this.energyLevel -= lostenergy;
    }


    // other
    public String toString(){
        return Integer.toString(this.energyLevel);
    }
}
