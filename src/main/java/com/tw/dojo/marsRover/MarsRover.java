package com.tw.dojo.marsRover;

import java.util.*;

public class MarsRover {

    public String run(String input) {
        String result = "";

        MarsRoverInputParser lines = new MarsRoverInputParser(input.split("\n"));

        for (int i = 0; i < lines.numberOfRovers(); i++) {
            Coordinate coordinates = Coordinate.getCoordinates(lines.get(coordinateLineIndex(i)));

            Direction direction = Direction.getDirection(lines.get(coordinateLineIndex(i)));

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

    public int coordinateLineIndex(int i) {
        return evenIndex(i) + 1;
    }

    public int evenIndex(int i) {
        return i * 2;
    }

    private List<Command> getCommands(MarsRoverInputParser lines, int commandLineIndex) {
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
}
