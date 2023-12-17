package model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int updateCount = 0;
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        updateCount++;
        System.out.println(message+ "\n" +worldMap.toString() + "\n" + "updates: " + updateCount+"\n"+"map UUID: " + worldMap.getId()+"\n");
    }
}
