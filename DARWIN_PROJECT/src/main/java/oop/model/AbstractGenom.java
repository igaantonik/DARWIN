package oop.model;

import java.util.*;

public class AbstractGenom implements Genom{
    protected List<Gen> genes;
    protected int currentGen;

    protected AnimalParameters parameters;


    // Getting atributes
    public List<Gen> getGenes() {
        return genes;
    }

    public int getCurrentGen(){
        return currentGen;
    }

    @Override
    public boolean equals(Object o) {
        if(getClass() != o.getClass()){
            return false;
        }
        if(this.genes.size() != ((AbstractGenom) o).getGenes().size()){
            return false;
        }
        for(int i=0; i< this.genes.size(); i++){
            if(!this.genes.get(i).equals( ((AbstractGenom) o).getGenes().get(i))){
                return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        return Objects.hash(genes);
    }

    // Moving animal

    @Override
    public MapDirection changeDirection(MapDirection currentDirection) {
        return currentDirection.rotate(this.genes.get(currentGen).toInteger());
    }

    @Override
    public Vector2d changePosition(Vector2d currentPosition, MapDirection currentDirection) {
        Vector2d move = currentDirection.toUnitVector();
        this.nextGen();
        return currentPosition.add(move);
    }

    @Override
    public void nextGen(){
        this.currentGen += 1;
        this.currentGen = this.currentGen % parameters.getGenomLength();
    }

    // Creating Gen list
    @Override
    public void randomGenes(){
        Random rand = new Random();
        List<Gen> new_genes = new ArrayList<>();
        for(int i=0; i < parameters.getGenomLength(); i++){
            new_genes.add(Gen.values()[rand.nextInt(8)]);
        }
        this.genes = new_genes;
    }
    @Override
    public void mutation() {
        Random rand = new Random();
        List<Gen> gens = this.genes;
        int max_gen = parameters.getMaxMutation();
        int min_gen = parameters.getMinMutation();
        int randomGensNumber = rand.nextInt((max_gen-min_gen)+1)+min_gen;
        for (int i = 0; i < randomGensNumber; i++){
            int index = rand.nextInt(gens.size());
            gens.remove(index);
            gens.add(index,Gen.values()[rand.nextInt(8)]);
            }
        this.genes = gens;
    }


    // Creating childs genom

    public List<Gen> childsgenom(Animal animal1, Animal animal2){
        Random rand = new Random();
        float gensFrom1 = ((float) animal1.getEnergyLevel() / (animal1.getEnergyLevel()+animal2.getEnergyLevel()))* parameters.getGenomLength();
        int animal1genes = Math.round(gensFrom1);
        if(rand.nextBoolean()){
                return createNewGenes(animal1.getGenom().getGenes(), animal2.getGenom().getGenes(), animal1genes);
            } else{
                return createNewGenes(animal2.getGenom().getGenes(), animal1.getGenom().getGenes(), parameters.getGenomLength()-animal1genes);
        }
    }

    public List<Gen> createNewGenes(List<Gen> genes1, List<Gen> genes2, int genesFromAnimal1){
        List<Gen> newgenes = new ArrayList<>();
        for (int i=0; i < genes1.size(); i++){
            if(i < genesFromAnimal1){
                newgenes.add(genes1.get(i));
            } else{
                newgenes.add(genes2.get(i));
            }
        }
        return newgenes;
    }


}
