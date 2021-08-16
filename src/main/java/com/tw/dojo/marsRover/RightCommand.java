package com.tw.dojo.marsRover;

public class RightCommand implements Command {
    @Override
    public Position execute(Position position) {
        Direction direction = position.getDirection().turnRight();
        return new Position(direction, position.getCoordinate());
    }
}
