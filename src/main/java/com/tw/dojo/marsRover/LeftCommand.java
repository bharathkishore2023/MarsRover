package com.tw.dojo.marsRover;

public class LeftCommand implements Command {

    @Override
    public Position execute(Position position) {
        Direction direction = position.getDirection().turnLeft();
        return new Position(direction, position.getCoordinate());
    }
}
