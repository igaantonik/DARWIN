package model;

public class Energy {
    int energyLevel;
    int leveltoReproduce;

    public Energy(int energylevel, int leveltoreproduce){
        this.energyLevel = energylevel;
        this.leveltoReproduce = leveltoreproduce;
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

    public void getEnergy(int addedenergy){
        this.energyLevel += addedenergy;
    }

    public void lostEnergy(int lostenergy){
        this.energyLevel -= lostenergy;
    }

    public boolean win(Energy enemyenergy){
        if(enemyenergy.energyLevel > this.energyLevel){
            return false;
        }
        return true;
    }
}
