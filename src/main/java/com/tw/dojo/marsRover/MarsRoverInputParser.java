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

    public String get(int positionLineIndex) {
        return inputLines[positionLineIndex];
    }

    public Coordinate getCoordinates(String inputLine) {
        int xWidth, yWidth;

        try {
            String[] split = inputLine.split(" ");

            xWidth = Integer.parseInt(split[0]);
            yWidth = Integer.parseInt(split[1]);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Could not parse position from: " + inputLine);
        }

        return new Coordinate(xWidth, yWidth);
    }

    public Direction getDirection(String inputLine) {
        try {
            return Direction.valueOf(inputLine.split(" ")[2]);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Could not parse direction from: " + inputLine);
        }
    }

    public List<Command> getCommands(int commandLineIndex) {
        String[] commandArray = get(commandLineIndex).split("(?!^)");

        List<String> validCommandsAsString = Arrays.asList("L", "R", "M");
        Map<String, Command> stringICommandMap = new HashMap() {{
            put("L", new LeftCommand());
            put("R", new RightCommand());
            put("M", new MoveCommand());
        }};

        List<Command> validCommands = new ArrayList<>();
        for (String command : commandArray) {
            if (!validCommandsAsString.contains(command)) {
                throw new IllegalArgumentException("Invalid command sequence: " + get(commandLineIndex));
            }
            validCommands.add(stringICommandMap.get(command));
        }
        return validCommands;
    }
}
