package com.tw.dojo.marsRover;

import java.util.List;

public class MarsRover {

    public String run(String input) {
        String result = "";

        MarsRoverInputParser inputParser = new MarsRoverInputParser(input.split("\n"));

        for (int i = 0; i < inputParser.numberOfRovers(); i++) {
            Coordinate coordinates = inputParser.getCoordinates(coordinateIndexAt(i));

            Direction direction = inputParser.getDirection(coordinateIndexAt(i));

            Position originalPosition = new Position(direction, coordinates);

            List<Command> commandArray = inputParser.getCommands(commandLineIndex(i));

            Position newPosition = null;
            for (Command command : commandArray) {
                newPosition = command.execute(originalPosition);
                originalPosition = newPosition;
            }

            result += newPosition.xCoordinate() + " " + newPosition.yCoordinate() + " " + newPosition.getDirection() + "\n";
        }

        return result;
    }

    public int commandLineIndex(int i) {
        return evenIndex(i) + 2;
    }

    public int coordinateIndexAt(int i) {
        return evenIndex(i) + 1;
    }

    public int evenIndex(int i) {
        return i * 2;
    }
}
