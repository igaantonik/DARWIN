package oop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenomBackAndForth extends AbstractGenom implements Genom{
    private int behaviour;

    public GenomBackAndForth(AnimalParameters parameters){
        this.parameters = parameters;
        randomGenes();
        this.currentGen = 0;
        this.behaviour = 1;
    }

    public GenomBackAndForth(Animal animal1, Animal animal2, AnimalParameters parameters){
        this.parameters = parameters;
        Random rand = new Random();
        genes = childsgenom(animal1,animal2);
        this.currentGen = rand.nextInt(genes.size());
        this.behaviour = 1;
    }


    public void nextGen(){
        if(currentGen == 0){
            this.behaviour = 1;
        }else if(currentGen == this.genes.size()-1){
            this.behaviour = -1;
        }
        currentGen += this.behaviour;
    }

}
