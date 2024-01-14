package oop.model;

import java.util.*;

public class RandomWaterBasinPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private int cellWidth;
    private int cellHeight;
    private final int amount;
    private final List<Vector2d> availablePositions = new ArrayList<>();
    private final Random random = new Random();

    public RandomWaterBasinPositionGenerator(int maxWidth, int maxHeight, int amount) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.amount = amount;
        this.cellWidth = (int) (Math.sqrt(maxWidth)/2);
        this.cellHeight = (int) (Math.sqrt(maxHeight)/2);




        for (int x = 0; x < maxWidth; x+=cellWidth) {
            for (int y = 0; y < maxHeight; y+=cellHeight) {
                availablePositions.add(new Vector2d(x, y));
            }
        }
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<Vector2d>() {
            private int generatedCount = 0;

            @Override
            public boolean hasNext() {
                return generatedCount < amount && !availablePositions.isEmpty();
            }

            @Override
            public Vector2d next() {
                if (hasNext()) {
                    int randomIndex = random.nextInt(availablePositions.size());
                    Vector2d generatedPosition = availablePositions.remove(randomIndex);
                    generatedCount++;

                    return generatedPosition;
                }

                throw new IllegalStateException("No more positions to generate.");
            }
        };
    }
}
