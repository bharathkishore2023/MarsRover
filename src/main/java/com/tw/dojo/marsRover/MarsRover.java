package com.tw.dojo.marsRover;

import com.tw.dojo.marsRover.command.Commands;
import com.tw.dojo.marsRover.model.Position;
import com.tw.dojo.marsRover.parser.MarsRoverInputParser;

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
