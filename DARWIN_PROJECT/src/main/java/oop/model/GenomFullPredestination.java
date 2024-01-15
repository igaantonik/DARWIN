package oop.model;

import java.util.Random;

public class GenomFullPredestination extends AbstractGenom implements Genom{

    public GenomFullPredestination(AnimalParameters parameters){
        this.parameters = parameters;
        randomGenes();
        this.currentGen = 0;
    }

    public GenomFullPredestination(Animal animal1, Animal animal2, AnimalParameters parameters){
        this.parameters = parameters;
        Random rand = new Random();
        genes = childsgenom(animal1,animal2);
        this.currentGen = rand.nextInt(genes.size());
    }
}
