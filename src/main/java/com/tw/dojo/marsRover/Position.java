package com.tw.dojo.marsRover;

public class Position {

    private Direction direction;
    private Coordinate coordinate;

    public Position(Direction direction, Coordinate coordinate) {
        this.direction = direction;
        this.coordinate = coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int xCoordinate(){
        return this.coordinate.x;
    }

    public int yCoordinate(){
        return this.coordinate.y;
    }

}
