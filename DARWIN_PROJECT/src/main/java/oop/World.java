package oop;

import oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        Earth map1 = new Earth(10, 10);
        List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(1,0), new Vector2d(2,2));
        Simulation simulation1 = new Simulation(positions, map1);
        MapChangeListener observer = new ConsoleMapDisplay();
        map1.addObserver(observer);
        simulation1.run(20);
        System.out.println("System zakończył dzialanie");
    }
}
