package com.tw.dojo.marsRover;

public class MarsRover {

    public String run(String input) {
        String result = "";

        MarsRoverInputParser inputParser = new MarsRoverInputParser(input.split("\n"));

        for (int roverIndex = 0; roverIndex < inputParser.numberOfRovers(); roverIndex++) {
            Position originalPosition = inputParser.getPosition(roverIndex);

            Commands commands = inputParser.getCommands(roverIndex);

            Position finalPosition = commands.execute(originalPosition);

            result += finalPosition.xCoordinate() + " " + finalPosition.yCoordinate() + " " + finalPosition.getDirection() + "\n";
        }

        return result;
    }
}
