package oop.model;

public class Energy {
    int energyLevel;
    int leveltoReproduce;

    public int getEnergyLevel() {
        return energyLevel;
    }

    public Energy(int energylevel, int leveltoreproduce){
        this.energyLevel = energylevel;
        this.leveltoReproduce = leveltoreproduce;
    }

    public String toString(){
        return Integer.toString(this.energyLevel);
    }

    public boolean enoughToReproduce(){
        if(this.energyLevel >= this.leveltoReproduce){
            return true;
        }
        return false;
    }

    public boolean canLive(){
        if(this.energyLevel == 0){
            return false;
        }
        return true;
    }

    public void addEnergy(int addedenergy){
        this.energyLevel += addedenergy;
    }

    public void lostEnergy(int lostenergy){
        this.energyLevel -= lostenergy;
    }

    public int win(Energy enemyenergy){
        if(enemyenergy.energyLevel > this.energyLevel){
            return -1;
        } else if(enemyenergy.energyLevel < this.energyLevel) {
            return 1;
        }
        return 0;
    }
}
