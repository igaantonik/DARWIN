package core.model;

import java.util.Map;

public class WaterBasin {
    private Map<Vector2d, Water> waterFields;
    private Vector2d upperRight;
    private Vector2d lowerLeft;
    private int flow;
    private MapDirection flowDirection;

    public WaterBasin(Vector2d upperRight, Vector2d lowerLeft) {
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
        this.flow = 1;                      // 1 - przypływ, -1 - odpływ
        this.flowDirection = MapDirection.NORTH;
    }

    public void changeFlow(){
        flow *= -1;
    }

    public void changeFlowDirection(MapDirection direction){
            flowDirection = direction;
    }

    public void changeBoundaries(){
        switch(flowDirection){
            case NORTH: upperRight.add(new Vector2d(0,flow));
            case NORTH_EAST: upperRight.add(new Vector2d(flow,flow));
            case EAST: upperRight.add(new Vector2d(flow,0));
            case SOUTH_EAST: lowerLeft.subtract(new Vector2d(0, flow)); upperRight.add(new Vector2d(flow, 0));
            case SOUTH: lowerLeft.subtract(new Vector2d(0, flow));
            case SOUTH_WEST: lowerLeft.subtract(new Vector2d(flow, flow));
            case WEST: lowerLeft.subtract(new Vector2d(flow, 0));
            case NORTH_WEST: lowerLeft.subtract(new Vector2d(flow, 0)); upperRight.add(new Vector2d(0, flow));
        }
    }

}
