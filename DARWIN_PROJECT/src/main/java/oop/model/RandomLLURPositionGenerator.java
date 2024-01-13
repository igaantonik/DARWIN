package oop.model;

import java.util.*;

public class RandomLLURPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int minWidth;
    private final int minHeight;
    private Vector2d startPosition;
    private final int amount;
    private final List<Vector2d> availablePositions = new ArrayList<>();
    private final Random random = new Random();

    public RandomLLURPositionGenerator(Vector2d startPosition, int width, int height) {
        this.maxWidth = startPosition.getX() + width -1;
        this.maxHeight = startPosition.getY() + height -1;
        this.minWidth = startPosition.getX();
        this.minHeight = startPosition.getY();
        this.amount = 2;
        for (int x = minWidth; x <= maxWidth; x++) {
            for (int y = minHeight; y <= maxHeight; y++) {
                availablePositions.add(new Vector2d(x, y));
            }
        }
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
                    Vector2d generatedPosition = availablePositions.get(randomIndex);
                    generatedCount++;
                    return generatedPosition;
                }

                throw new IllegalStateException("No more positions to generate.");
            }
        };
    }
}
