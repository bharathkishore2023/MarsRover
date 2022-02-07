package com.tw.dojo.marsRover;

import java.util.*;

public class MarsRover {

    public String run(String input) {
        String result = "";

        InputLines lines = new InputLines(input.split("\n"));

        int numberOfRovers = lines.numberOfRovers();

        for (int i = 0; i < numberOfRovers; i++) {
            Coordinate coordinates = getCoordinates(lines.get(positionLineIndex(i)));

            Direction direction = getDirection(lines.get(positionLineIndex(i)));

            Position originalPosition = new Position(direction, coordinates);

            List<Command> commandArray = getCommands(lines, commandLineIndex(i));
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

    public int positionLineIndex(int i) {
        return evenIndex(i) + 1;
    }

    public int evenIndex(int i) {
        return i * 2;
    }

    private List<Command> getCommands(InputLines lines, int commandLineIndex) {
        String[] commandArray = lines.get(commandLineIndex).split("(?!^)");

        List<String> validCommandsAsString = Arrays.asList("L", "R", "M");
        Map<String, Command> stringICommandMap = new HashMap() {{
            put("L", new LeftCommand());
            put("R", new RightCommand());
            put("M", new MoveCommand());
        }};

        List<Command> validCommands = new ArrayList<>();
        for (String command : commandArray) {
            if (!validCommandsAsString.contains(command)) {
                throw new IllegalArgumentException("Invalid command sequence: " + lines.get(commandLineIndex));
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
