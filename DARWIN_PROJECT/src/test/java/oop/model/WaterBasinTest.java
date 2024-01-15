package oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterBasinTest {
    @Test
    void changeFlow_ReversesFlow() {
        WaterBasin waterBasin = new WaterBasin(new Vector2d(3, 4), new Vector2d(1, 2));

        assertEquals(1, waterBasin.getFlow());
        waterBasin.changeFlow();
        assertEquals(-1, waterBasin.getFlow());
        waterBasin.changeFlow();
        assertEquals(1, waterBasin.getFlow());
    }

    @Test
    void changeFlowDirection_UpdatesFlowDirection() {
        WaterBasin waterBasin = new WaterBasin(new Vector2d(3, 4), new Vector2d(1, 2));

        assertEquals(MapDirection.NORTH, waterBasin.getFlowDirection());
        waterBasin.changeFlowDirection(MapDirection.EAST);
        assertEquals(MapDirection.EAST, waterBasin.getFlowDirection());
        waterBasin.changeFlowDirection(MapDirection.SOUTH_WEST);
        assertEquals(MapDirection.SOUTH_WEST, waterBasin.getFlowDirection());
    }

    @Test
    void changeBoundaries_UpdatesBoundariesBasedOnFlowDirection() {
        WaterBasin waterBasin = new WaterBasin(new Vector2d(3, 4), new Vector2d(1, 2));

        assertEquals(new Vector2d(3, 4), waterBasin.getUpperRight());
        assertEquals(new Vector2d(1, 2), waterBasin.getLowerLeft());

        waterBasin.changeFlowDirection(MapDirection.EAST);
        waterBasin.changeBoundaries();
        assertEquals(new Vector2d(4, 4), waterBasin.getUpperRight());
        assertEquals(new Vector2d(1, 2), waterBasin.getLowerLeft());

        waterBasin.changeFlowDirection(MapDirection.SOUTH_WEST);
        waterBasin.changeBoundaries();
        assertEquals(new Vector2d(4, 4), waterBasin.getUpperRight());
        assertEquals(new Vector2d(0, 1), waterBasin.getLowerLeft());

        waterBasin.changeFlow();
        waterBasin.changeFlowDirection(MapDirection.SOUTH_WEST);
        waterBasin.changeBoundaries();
        assertEquals(new Vector2d(4, 4), waterBasin.getUpperRight());
        assertEquals(new Vector2d(1, 2), waterBasin.getLowerLeft());

        waterBasin.changeFlowDirection(MapDirection.EAST);
        waterBasin.changeBoundaries();
        assertEquals(new Vector2d(3, 4), waterBasin.getUpperRight());
        assertEquals(new Vector2d(1, 2), waterBasin.getLowerLeft());
    }
}