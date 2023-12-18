package core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenomBackAndForth extends AbstractGenom implements Genom{
    private int behaviour;

    public void mutation() {
        Random rand = new Random();
        List<Integer> new_genes = new ArrayList<>();
        int max_gen = 8;
        for (int i = 0; i < 20; i++){
            new_genes.add(rand.nextInt(max_gen));
        }
        this.genes=new_genes;
    }
    public GenomBackAndForth(List <Integer> genes){
        this.genes = genes;
        this.currentGen = 0;
        this.behaviour = 1;
    }

    public void nextGen(){
        if(currentGen == 0){
            this.behaviour = 1;
        }else if(currentGen == genes.size()-1){
            this.behaviour = -1;
        }
        currentGen += this.behaviour;
    }

}
