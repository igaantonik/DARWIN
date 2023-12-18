package core.model;

import java.util.*;

public class AbstractGenom implements Genom {
    protected List<Integer> genes;
    protected int currentGen;


    @Override
    public void mutation() {
        Random rand = new Random();
        List<Integer> new_genes = new ArrayList<>();
        int max_gen = 8;
        for (int i = 0; i < 20; i++){
            new_genes.add(rand.nextInt(max_gen));
        }
        this.genes=new_genes;
    }

    @Override
    public MapDirection changeDirection(MapDirection currentDirection) {
        MapDirection newDirection = currentDirection.rotate(this.genes.get(currentGen));
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
