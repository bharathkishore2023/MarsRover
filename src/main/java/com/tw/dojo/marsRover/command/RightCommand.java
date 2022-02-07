package com.tw.dojo.marsRover.command;

import com.tw.dojo.marsRover.model.Direction;
import com.tw.dojo.marsRover.model.Position;

public class RightCommand implements Command {
    @Override
    public Position execute(Position position) {
        Direction direction = position.getDirection().turnRight();
        return new Position(direction, position.getCoordinate());
    }
}
