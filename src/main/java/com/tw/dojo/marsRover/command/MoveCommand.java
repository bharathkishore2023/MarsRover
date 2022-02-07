package com.tw.dojo.marsRover.command;

import com.tw.dojo.marsRover.model.Coordinate;
import com.tw.dojo.marsRover.model.Direction;
import com.tw.dojo.marsRover.model.Position;

public class MoveCommand implements Command {

    @Override
    public Position execute(Position position) {
        Coordinate coordinates = position.getCoordinate();
        Direction direction = position.getDirection();
        return new Position(direction, coordinates.update(direction));
    }
}
