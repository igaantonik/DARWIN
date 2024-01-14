package oop.model;

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
}
