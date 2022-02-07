package com.tw.dojo.marsRover;

import java.util.List;

public class MarsRover {

    public String run(String input) {
        String result = "";

        MarsRoverInputParser inputParser = new MarsRoverInputParser(input.split("\n"));

        for (int roverIndex = 0; roverIndex < inputParser.numberOfRovers(); roverIndex++) {
            Position originalPosition = inputParser.getPosition(roverIndex);

            List<Command> commandArray = inputParser.getCommands(roverIndex);

            Position position = null;
            for (Command command : commandArray) {
                position = command.execute(originalPosition);
                originalPosition = position;
            }

            result += position.xCoordinate() + " " + position.yCoordinate() + " " + position.getDirection() + "\n";
        }

        return result;
    }
}
