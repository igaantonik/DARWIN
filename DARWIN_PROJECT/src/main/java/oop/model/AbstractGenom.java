package oop.model;

import java.util.*;

public class AbstractGenom implements Genom {
    protected List<Gen> genes;
    protected int currentGen;


    @Override
    public void randomGenes(){
        Random rand = new Random();
        List<Gen> new_genes = new ArrayList<>();
        for(int i=0; i < 20; i++){
            new_genes.add(Gen.values()[rand.nextInt(8)]);
        }
        this.genes = new_genes;
    }
    @Override
    public void mutation() {
        Random rand = new Random();
        List<Gen> new_genes = new ArrayList<>();
        List<Gen> gens = this.genes;
        int max_gen = 8;
        int randomGensNumber = rand.nextInt(20);
        for (int i = 0; i < randomGensNumber; i++){
            gens.remove(i);
            gens.add(i,Gen.values()[rand.nextInt(8)]);
            }
        this.genes = gens;
    }

    public List<Gen> getGenes() {
        return genes;
    }

    public List<Gen> childsgenom(Animal animal1, Animal animal2){
        Random rand = new Random();
        float gensFrom1 = ((float) animal1.getEnergyLevel() / (animal1.getEnergyLevel()+animal2.getEnergyLevel()))*genes.size();
        int animal1genes = Math.round(gensFrom1);
        if(rand.nextBoolean()){
                return createNewGenes(animal1.getGenom().getGenes(), animal2.getGenom().getGenes(), animal1genes);
            } else{
                return createNewGenes(animal2.getGenom().getGenes(), animal1.getGenom().getGenes(), genes.size()-animal1genes);
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

    @Override
    public MapDirection changeDirection(MapDirection currentDirection) {
        MapDirection newDirection = currentDirection.rotate(this.genes.get(currentGen).toInteger());
        return newDirection;
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
    }


}
