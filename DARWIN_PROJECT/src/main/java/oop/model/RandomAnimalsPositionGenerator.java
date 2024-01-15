package oop.model;

import java.util.*;

public class RandomAnimalsPositionGenerator implements Iterable, Iterator<Vector2d> {
    private List<Vector2d> positions;
    private int current_position;
    private int positions_len;

    public RandomAnimalsPositionGenerator(int maxWidth, int maxHeight, int animalCount){
        Random rand = new Random();
        this.current_position = 0;
        this.positions_len = animalCount;
        ArrayList<Integer> list_x = new ArrayList<Integer>();
        for (int i=0; i < animalCount; i++) list_x.add(rand.nextInt(0,maxWidth-1));
        Collections.shuffle(list_x);

        ArrayList<Integer> list_y = new ArrayList<Integer>();
        for (int i=0; i < animalCount; i++) list_y.add(rand.nextInt(0,maxHeight-1));
        Collections.shuffle(list_y);

        List<Vector2d> positions = new ArrayList<>();
        for (int i=0; i<animalCount; i++) positions.add(new Vector2d(list_x.get(i), list_y.get(i)));
        this.positions = positions;
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return this.positions.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.current_position < this.positions_len-1;
    }

    @Override
    public Vector2d next() {
        this.current_position ++;
        return this.positions.get(current_position-1);
    }
}
