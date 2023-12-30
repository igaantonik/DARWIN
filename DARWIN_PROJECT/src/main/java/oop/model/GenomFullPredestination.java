package oop.model;

import java.util.ArrayList;
import java.util.List;

public class GenomFullPredestination extends AbstractGenom implements Genom{
    private int currentGen;

    public GenomFullPredestination() {
        randomGenes();
        this.currentGen = 0;
    }
}
