package oop.model;

import java.util.*;

public class RandomPlantPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int amount;
    private final List<Vector2d> availablePositions = new ArrayList<>();
    private final List<Vector2d> junglePositions = new ArrayList<>();
    private final Random random = new Random();

    public RandomPlantPositionGenerator(int maxWidth, int maxHeight, int amount, Map<Vector2d, Plant> plants) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.amount = amount;

        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                availablePositions.add(new Vector2d(x, y));
            }
        }

        for (int x = 0; x < maxWidth; x++) {
            for (int y = (maxHeight*4)/10; y < (maxHeight*6)/10; y++) {
                junglePositions.add(new Vector2d(x, y));
            }
        }

        for (Vector2d position : plants.keySet()) {
            availablePositions.remove(position);
            junglePositions.remove(position);
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<Vector2d>() {
            private int generatedCount = 0;

            @Override
            public boolean hasNext() {
                return generatedCount < amount;
            }

            @Override
            public Vector2d next() {
                if (hasNext()) {
                    int variant = random.nextInt(10);
                    Vector2d generatedPosition;
                    if (variant<=2){
                        int randomIndex = random.nextInt(availablePositions.size());
                        generatedPosition = availablePositions.remove(randomIndex);
                        generatedCount++;
                    }
                    else{
                        int randomIndex = random.nextInt(junglePositions.size());
                        generatedPosition = junglePositions.remove(randomIndex);
                        availablePositions.remove(randomIndex);
                        generatedCount++;
                    }


                    return generatedPosition;
                }

                throw new IllegalStateException("No more positions to generate.");
            }
        };
    }
}