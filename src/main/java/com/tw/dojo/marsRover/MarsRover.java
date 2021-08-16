package com.tw.dojo.marsRover;

import java.util.*;

public class MarsRover {

    public String run(String input) {
        String result = "";

        String[] lines = input.split("\n");

        int numberOfRovers = (lines.length - 1) / 2;

        for (int i = 0; i < numberOfRovers; i++) {
            int positionLineIndex = i * 2 + 1;
            int commandLineIndex = positionLineIndex + 1;

            Coordinate coordinates = getCoordinates(lines[positionLineIndex]);

            Direction direction = getDirection(lines[positionLineIndex]);

            Position originalPosition = new Position(direction, coordinates);

            List<Command> commandArray = getCommands(lines, commandLineIndex);
            Position newPosition = null;
            for (Command command : commandArray) {
                newPosition = command.execute(originalPosition);
                originalPosition = newPosition;
            }

            result += newPosition.xCoordinate() + " " + newPosition.yCoordinate() + " " + newPosition.getDirection() + "\n";
        }

        return result;
    }

    private List<Command> getCommands(String[] lines, int commandLineIndex) {
        String[] commandArray = lines[commandLineIndex].split("(?!^)");

        List<String> validCommandsAsString = Arrays.asList("L", "R", "M");
        Map<String, Command> stringICommandMap = new HashMap() {{
            put("L", new LeftCommand());
            put("R", new RightCommand());
            put("M", new MoveCommand());
        }};

        List<Command> validCommands = new ArrayList<>();
        for (String command : commandArray) {
            if (!validCommandsAsString.contains(command)) {
                throw new IllegalArgumentException("Invalid command sequence: " + lines[commandLineIndex]);
            }
            validCommands.add(stringICommandMap.get(command));
        }
        return validCommands;
    }

    private Direction getDirection(String line) {
        try {
            return Direction.valueOf(line.split(" ")[2]);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Could not parse direction from: " + line);
        }
    }

    private Coordinate getCoordinates(String line) {
        int xWidth, yWidth;

        try {
            String[] split = line.split(" ");

            xWidth = Integer.parseInt(split[0]);
            yWidth = Integer.parseInt(split[1]);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Could not parse position from: " + line);
        }

        return new Coordinate(xWidth, yWidth);
    }
}
