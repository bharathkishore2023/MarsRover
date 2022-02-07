package com.tw.dojo.marsRover;

import com.tw.dojo.marsRover.command.Commands;
import com.tw.dojo.marsRover.model.Position;
import com.tw.dojo.marsRover.parser.MarsRoverInputParser;

public class MarsRover {

    public String run(String input) {
        StringBuilder result = new StringBuilder();

        MarsRoverInputParser inputParser = new MarsRoverInputParser(input.split("\n"));

        for (int roverIndex = 0; roverIndex < inputParser.numberOfRovers(); roverIndex++) {
            Position originalPosition = inputParser.getPosition(roverIndex);

            Commands commands = inputParser.getCommands(roverIndex);

            Position finalPosition = commands.execute(originalPosition);

            result.append(finalPosition.xCoordinate()).append(" ").append(finalPosition.yCoordinate()).append(" ").append(finalPosition.getDirection()).append("\n");
        }

        return result.toString();
    }
}
