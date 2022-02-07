package com.tw.dojo.marsRover;

import java.util.*;

public class MarsRoverInputParser {
    private String[] inputLines;

    public MarsRoverInputParser(String[] inputLines) {
        this.inputLines = inputLines;
    }

    public int numberOfRovers() {
        return (inputLines.length - 1) / 2;
    }

    public String inputAt(int positionLineIndex) {
        return inputLines[positionLineIndex];
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

    public List<Command> getCommands(int commandLineIndex) {
        String[] commandArray = inputAt(commandLineIndex).split("(?!^)");

        List<String> validCommandsAsString = Arrays.asList("L", "R", "M");
        Map<String, Command> stringICommandMap = new HashMap() {{
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
        return validCommands;
    }
}
