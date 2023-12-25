package oop.model;

import java.util.List;

public class GenomFullPredestination extends AbstractGenom implements Genom{
    private List<Integer> genes;
    private int currentGen;

    public GenomFullPredestination(List<Integer> genes) {
        this.genes = genes;
        this.currentGen = 0;
    }
}
