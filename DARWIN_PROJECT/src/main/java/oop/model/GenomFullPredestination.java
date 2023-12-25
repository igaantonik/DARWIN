package oop.model;

import java.util.List;

public class GenomFullPredestination extends AbstractGenom implements Genom{
    private int currentGen;

    public GenomFullPredestination() {
        this.genes = mutation();
        this.currentGen = 0;
    }
}
