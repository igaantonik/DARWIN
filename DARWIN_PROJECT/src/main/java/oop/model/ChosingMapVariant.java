package oop.model;

public class ChosingMapVariant {

    public static WorldMap createMap(WorldParameters worldParameters){
        switch (worldParameters.getMapWariant()){
            case 1 -> {
                return new FlowsAndEbbs(worldParameters);
            }
            case 0 -> {
                return new Earth(worldParameters);
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
