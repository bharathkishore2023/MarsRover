package com.tw.dojo.marsRover;

import java.util.ArrayList;
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

            List<Command> commandArray = getCommands(lines, commandLineIndex);

            for (Command command : commandArray) {
                if (command.equals(Command.M)) {
                    coordinates = coordinates.update(direction);
                } else if (command.equals(Command.R)) {
                    direction = direction.turnRight();
                } else if (command.equals(Command.L)) {
                    direction = direction.turnLeft();
                }
            }

            result += coordinates.x + " " + coordinates.y + " " + direction + "\n";
        }

        return result;
    }

    private List<Command> getCommands(String[] lines, int commandLineIndex) {
        String[] commandArray = lines[commandLineIndex].split("(?!^)");

        List<String> validCommandsAsString = Arrays.asList("L", "R", "M");
        List<Command> validCommands = new ArrayList<>();
        for (String command : commandArray) {
            if (!validCommandsAsString.contains(command)) {
                throw new IllegalArgumentException("Invalid command sequence: " + lines[commandLineIndex]);
            }
            validCommands.add(Command.valueOf(command));
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
