package oop.model;

public class ChosingMapVariant {

    public static WorldMap createMap(WorldParameters worldParameters){
        switch (worldParameters.getMapWariant()){
//            case 0 -> {
//                return new AbbsandFlows(worldParameters);
//            }
            case 1 -> {
                return new Earth(worldParameters);
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
