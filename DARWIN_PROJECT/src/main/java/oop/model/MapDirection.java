package oop.model;

public enum MapDirection {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;


    public String toString(){
        return switch(this){
            case NORTH -> "N";
            case NORTH_EAST -> "NE";
            case EAST -> "E";
            case SOUTH_EAST -> "SE";
            case SOUTH -> "S";
            case SOUTH_WEST -> "SW";
            case WEST -> "W";
            case NORTH_WEST -> "NW";
        };
    }

    public MapDirection rotate(int rotatenumber){
        MapDirection[] values = MapDirection.values();
        return values[(this.ordinal() + rotatenumber) % values.length];
    }
    public MapDirection previous(){
        MapDirection[] values = MapDirection.values();
        return values[(this.ordinal() - 1 + values.length) % values.length];
    }

    public MapDirection next(){
        MapDirection[] values = MapDirection.values();
        return values[(this.ordinal() + 1) % values.length];
    }

    public Vector2d toUnitVector(){
        return switch(this){
            case NORTH -> new Vector2d(0,1);
            case SOUTH -> new Vector2d(0,-1);
            case NORTH_EAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case WEST -> new Vector2d(-1,0);
            case SOUTH_EAST -> new Vector2d(1,-1);
            case SOUTH_WEST -> new Vector2d(-1,-1);
            case NORTH_WEST -> new Vector2d(-1,1);
        };
    }

}
