package oop.model;

import java.util.HashMap;
import java.util.Map;

public class WaterBasin {
    private Map<Vector2d, Water> waterFields = new HashMap<>();
    private Vector2d upperRight;
    private Vector2d lowerLeft;
    private int flow;
    private MapDirection flowDirection;

    public WaterBasin(Vector2d upperRight, Vector2d lowerLeft) {
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
        this.flow = 1;                      // 1 - przypływ, -1 - odpływ
        this.flowDirection = MapDirection.NORTH;
        fillFields();
    }

    public void fillFields(){
        int leftX = lowerLeft.getX();
        int upY = upperRight.getY();
        int rightX = upperRight.getX();
        int downY = lowerLeft.getY();
        for( int i = leftX; i <= rightX; i++) {
            for(int j = downY; j <= upY; j++) {
                Vector2d pos = new Vector2d(i,j);
                waterFields.put(pos,new Water(pos));
            }
        }
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public void changeFlow(){
        flow *= -1;
    }

    public void changeFlowDirection(MapDirection direction){
            flowDirection = direction;
    }

    public void changeBoundaries(){
        switch(flowDirection){
            case NORTH: upperRight = upperRight.add(new Vector2d(0,flow)); break;
            case NORTH_EAST:upperRight = upperRight.add(new Vector2d(flow,flow)); break;
            case EAST: upperRight = upperRight.add(new Vector2d(flow,0));break;
            case SOUTH_EAST: lowerLeft = lowerLeft.subtract(new Vector2d(0, flow)); upperRight =upperRight.add(new Vector2d(flow, 0));break;
            case SOUTH: lowerLeft = lowerLeft.subtract(new Vector2d(0, flow));break;
            case SOUTH_WEST: lowerLeft = lowerLeft.subtract(new Vector2d(flow, flow));break;
            case WEST: lowerLeft = lowerLeft.subtract(new Vector2d(flow, 0));break;
            case NORTH_WEST: lowerLeft = lowerLeft.subtract(new Vector2d(flow, 0)); upperRight = upperRight.add(new Vector2d(0, flow));break;
        }
    }

    public MapDirection getFlowDirection(){
        return flowDirection;
    }

    public int getFlow(){return flow;}
    @Override
    public String toString() {
        return flowDirection + " " + flow + " " + upperRight + " " +lowerLeft;
    }
}
