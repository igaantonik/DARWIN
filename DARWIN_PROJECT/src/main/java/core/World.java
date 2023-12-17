package core;

import core.model.ConsoleMapDisplay;
import core.model.MapChangeListener;
import core.model.TestMap;
import core.model.Vector2d;

import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        TestMap map1 = new TestMap(10, 10);
        List<Vector2d> positions = List.of(new Vector2d(5, 5));
        Simulation simulation1 = new Simulation(positions, map1);
        MapChangeListener observer = new ConsoleMapDisplay();
        map1.addObserver(observer);
        simulation1.run(20);
        System.out.println("System zakończył dzialanie");
    }
}
