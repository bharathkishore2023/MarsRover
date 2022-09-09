package com.tw.dojo.marsRover.parser;

import com.tw.dojo.marsRover.command.*;
import com.tw.dojo.marsRover.model.Coordinate;
import com.tw.dojo.marsRover.model.Direction;
import com.tw.dojo.marsRover.model.Position;

import java.util.*;

public class MarsRoverInputParser {
    private final String[] inputLines;

    public MarsRoverInputParser(String[] inputLines) {
        this.inputLines = inputLines;
    }

    public int numberOfRovers() {
        return (inputLines.length - 1) / 2;
    }

    private String inputAt(int index) {
        return inputLines[index];
    }

    public Coordinate getCoordinates(int coordinateIndex) {
        String coordinates = inputAt(coordinateIndex);
        int xWidth, yWidth;

        try {
            String[] splitCoordinates = coordinates.split(" ");

            xWidth = Integer.parseInt(splitCoordinates[0]);
            yWidth = Integer.parseInt(splitCoordinates[1]);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Could not parse position from: " + coordinateIndex);
        }

        return new Coordinate(xWidth, yWidth);
    }

    public Direction getDirection(int directionIndex) {
        String initialDirection = inputAt(directionIndex);
        try {
            return Direction.valueOf(initialDirection.split(" ")[2]);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Could not parse direction from: " + directionIndex);
        }
    }

    public Commands getCommands(int commandLineIndex) {
        String[] commandArray = inputAt(evenIndex(commandLineIndex) + 2).split("(?!^)");

        List<String> validCommandsAsString = Arrays.asList("L", "R", "M");
        Map<String, Command> stringICommandMap = new HashMap<String,Command>() {{
            put("L", new LeftCommand());
            put("R", new RightCommand());
            put("M", new MoveCommand());
        }};

        List<Command> validCommands = new ArrayList<>();
        for (String command : commandArray) {
            if (!validCommandsAsString.contains(command)) {
                throw new IllegalArgumentException("Invalid command sequence: " + inputAt(commandLineIndex));
            }
            validCommands.add(stringICommandMap.get(command));
        }
        return new Commands(validCommands);
    }

    public Position getPosition(int positionIndex) {
        Coordinate coordinates = getCoordinates(evenIndex(positionIndex) + 1);

        Direction direction = getDirection(evenIndex(positionIndex) + 1);

        return new Position(direction, coordinates);
    }

    private int evenIndex(int i) {
        return i * 2;
    }
}
