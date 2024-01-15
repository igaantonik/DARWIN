package oop.model;

import java.util.stream.Collectors;

public class  AnimalStatistics implements ChangeStats{
    private Animal animal;
    private Genom genom;
    private int activeGenomPart;
    private int energyLevel;
    private int plantsEaten;
    private int kidsNumber;
    private int offspringNumber;
    private int age;
    private int deathDate;

    public AnimalStatistics(Animal animal){
        this.animal = animal;
        this.deathDate = -1; //jeszcze zyje
    }

    @Override
    public void statsChanged() {
        this.genom = animal.getGenom();
        this.activeGenomPart = genom.getCurrentGen();
        this.energyLevel = animal.getEnergyLevel();
        this.plantsEaten = animal.getPlantsEaten();
        this.kidsNumber = animal.howManyChildren();
        this.offspringNumber = animal.howManyOffspring();
        this.age = animal.getAge();
        if(animal.isDeceased()){
            this.deathDate = animal.getAge() ;
        }
    }

    public String getGenom() {
        return this.genom.getGenes().stream().map(Gen::toString)
                .collect(Collectors.joining(" "));
    }

    public String getEnergyLevel() {
        return Integer.toString(this.energyLevel);
    }

    public String getAge() {
        return Integer.toString(age);
    }

    public String getKidsNumber() {
        return Integer.toString(kidsNumber);
    }

    public String getActiveGenomPart() {
        return Integer.toString(activeGenomPart);
    }

    public String getDeathDate() {
        return Integer.toString(deathDate);
    }

    public String getOffspringNumber() {
        return Integer.toString(offspringNumber);
    }

    public String getPlantsEaten() {
        return Integer.toString(plantsEaten);
    }
}
