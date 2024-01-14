package oop.model;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;

    // constructor
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }


    // Getting atributes
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    // Wrapping for Earth map
    public Vector2d enwrapping(int width){
        width -=1;
        Vector2d new_vector = new Vector2d(((this.x % (width)) + width)%width, this.y);
        return new_vector;
    }

    // Other

    boolean precedes(Vector2d other){
        if(other.x >= this.x && other.y >= this.y){
            return true;
        } else return false;
    }

    boolean follows(Vector2d other){
        if(other.x <= this.x && other.y <= this.y){
            return true;
        } else return false;
    }

    Vector2d add(Vector2d other){
        int x3 = this.x + other.x;
        int y3 = this.y + other.y;
        return new Vector2d(x3, y3);
    }

    Vector2d subtract(Vector2d other){
        int x3 = this.x - other.x;
        int y3 = this.y - other.y;
        return new Vector2d(x3, y3);
    }

    Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    public String toString(){
        return "(" + this.x + "," + this.y +")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vector2d)) {
            return false;
        }
        Vector2d that = (Vector2d) other;
        if (that.x == this.x && that.y == this.y) {
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

}
