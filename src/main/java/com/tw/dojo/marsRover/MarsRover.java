package com.tw.dojo.marsRover;

import java.util.Arrays;
import java.util.List;

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

            String[] commandArray = getCommandArray(lines, commandLineIndex);

            for (String command : commandArray) {
                if (command.equals("M")) {
                    coordinates = coordinates.update(direction);
                } else if (command.equals("R")) {
                    direction = direction.turnRight();
                } else if (command.equals("L")) {
                    direction = direction.turnLeft();
                }
            }

            result += coordinates.x + " " + coordinates.y + " " + direction + "\n";
        }

        return result;
    }

    private String[] getCommandArray(String[] lines, int commandLineIndex) {
        String[] commandArray = lines[commandLineIndex].split("(?!^)");

        List<String> validCommands = Arrays.asList("L", "R", "M");
        for (String command : commandArray) {
            if (!validCommands.contains(command)) {
                throw new IllegalArgumentException("Invalid command sequence: " + lines[commandLineIndex]);
            }
        }
        return commandArray;
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

    private static Coordinate move(Coordinate coordinate, Direction direction) {
        if (direction.equals(Direction.N)) {
            coordinate.y += +1;
        } else if (direction.equals(Direction.S)) {
            coordinate.y += -1;
        } else if (direction.equals(Direction.E)) {
            coordinate.x += +1;
        } else if (direction.equals(Direction.W)) {
            coordinate.x += -1;
        }

        return coordinate;
    }

}
