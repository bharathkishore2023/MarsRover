package com.tw.dojo.marsRover;

public class MoveCommand implements Command {

    @Override
    public Position execute(Position position) {
        Coordinate coordinates = position.getCoordinate();
        Direction direction = position.getDirection();
        return new Position(direction, coordinates.update(direction));
    }
}
