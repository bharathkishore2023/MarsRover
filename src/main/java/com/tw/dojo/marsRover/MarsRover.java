package com.tw.dojo.marsRover;

import java.util.List;

public class MarsRover {

    public String run(String input) {
        String result = "";

        MarsRoverInputParser inputParser = new MarsRoverInputParser(input.split("\n"));

        for (int i = 0; i < inputParser.numberOfRovers(); i++) {
            Coordinate coordinates = inputParser.getCoordinates(evenIndex(i) + 1);

            Direction direction = inputParser.getDirection(evenIndex(i) + 1);

            Position originalPosition = new Position(direction, coordinates);

            List<Command> commandArray = inputParser.getCommands(evenIndex(i) + 2);

            Position position = null;
            for (Command command : commandArray) {
                position = command.execute(originalPosition);
                originalPosition = position;
            }

            result += position.xCoordinate() + " " + position.yCoordinate() + " " + position.getDirection() + "\n";
        }

        return result;
    }

    public int evenIndex(int i) {
        return i * 2;
    }
}
