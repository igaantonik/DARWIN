package oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

        @Test
        public void nextTest_NORTH(){
            MapDirection direction = MapDirection.NORTH;
            MapDirection result = direction.next();
            assertEquals(MapDirection.NORTH_EAST, result);
        }
        @Test
        public void nextTest_EAST(){
            MapDirection direction = MapDirection.EAST;
            MapDirection result = direction.next();
            assertEquals(MapDirection.SOUTH_EAST, result);
        }
        @Test
        public void nextTest_WEST(){
            MapDirection direction = MapDirection.WEST;
            MapDirection result = direction.next();
            assertEquals(MapDirection.NORTH_WEST, result);
        }
        @Test
        public void nextTest_SOUTH(){
            MapDirection direction = MapDirection.SOUTH;
            MapDirection result = direction.next();
            assertEquals(MapDirection.SOUTH_WEST, result);
        }

        @Test
        public void previousTest_SOUTH(){
            MapDirection direction = MapDirection.SOUTH;
            MapDirection result = direction.previous();
            assertEquals(MapDirection.SOUTH_EAST, result);
        }

        @Test
        public void previousTest_EAST(){
            MapDirection direction = MapDirection.EAST;
            MapDirection result = direction.previous();
            assertEquals(MapDirection.NORTH_EAST, result);
        }

        @Test
        public void previousTest_NORTH(){
            MapDirection direction = MapDirection.NORTH;
            MapDirection result = direction.previous();
            assertEquals(MapDirection.NORTH_WEST, result);
        }

        @Test
        public void previousTest_WEST(){
            MapDirection direction = MapDirection.WEST;
            MapDirection result = direction.previous();
            assertEquals(MapDirection.SOUTH_WEST, result);
        }


    @Test
    void rotate_NorthBy0_ReturnsNorth() {
        MapDirection initialDirection = MapDirection.NORTH;
        MapDirection rotatedDirection = initialDirection.rotate(0);
        assertEquals(initialDirection, rotatedDirection);
    }

    @Test
    void rotate_NorthBy1_ReturnsNorthEast() {
        MapDirection initialDirection = MapDirection.NORTH;
        MapDirection rotatedDirection = initialDirection.rotate(1);
        assertEquals(MapDirection.NORTH_EAST, rotatedDirection);
    }

    @Test
    void rotate_SouthBy2_ReturnsSouthWest() {
        MapDirection initialDirection = MapDirection.SOUTH;
        MapDirection rotatedDirection = initialDirection.rotate(2);
        assertEquals(MapDirection.WEST, rotatedDirection);
    }

    @Test
    void rotate_WestBy4_ReturnsWest() {
        MapDirection initialDirection = MapDirection.WEST;
        MapDirection rotatedDirection = initialDirection.rotate(4);
        assertEquals(MapDirection.EAST, rotatedDirection);
    }

    @Test
    void rotate_EastByMinus1_ReturnsWest() {
        MapDirection initialDirection = MapDirection.EAST;
        MapDirection rotatedDirection = initialDirection.rotate(-1);
        assertEquals(MapDirection.NORTH_EAST, rotatedDirection);
    }

    @Test
    void rotate_SouthEastBy7_ReturnsNorthEast() {
        MapDirection initialDirection = MapDirection.SOUTH_EAST;
        MapDirection rotatedDirection = initialDirection.rotate(7);
        assertEquals(MapDirection.EAST, rotatedDirection);
    }
}