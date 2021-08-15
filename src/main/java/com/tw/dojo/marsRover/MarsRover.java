package com.tw.dojo.marsRover;

import java.util.Arrays;
import java.util.List;

public class MarsRover {

    public String run(String input) {
        String out = "";

        String[] lines = input.split("\n");

        int numberOfRovers = (lines.length - 1) / 2;

        for (int i = 0; i < numberOfRovers; i++) {
            int positionLineIndex = i * 2 + 1;
            int commandLineIndex = positionLineIndex + 1;

            int[] position = getPosition(lines[positionLineIndex]);

            String direction = getDirection(lines[positionLineIndex]);

            String[] commandArray = getCommandArray(lines, commandLineIndex);

            for (String command : commandArray) {
                if (command.equals("M")) {
                    position = move(position, direction);
                } else if (command.equals("R")) {
                    direction = turnRight(direction);
                } else if (command.equals("L")) {
                    direction = turnLeft(direction);
                }
            }

            out += position[0] + " " + position[1] + " " + direction + "\n";
        }

        return out;
    }

    private String turnLeft(String direction) {
        List<String> all = Arrays.asList("N", "E", "S", "W");
        direction = all.get((all.indexOf(direction) + 3) % all.size());
        return direction;
    }

    private String turnRight(String direction) {
        List<String> all = Arrays.asList("N", "E", "S", "W");
        direction = all.get((all.indexOf(direction) + 1) % all.size());
        return direction;
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

    private String getDirection(String line) {
        String direction;

        try {
            direction = line.split(" ")[2];
            if (!Arrays.asList("N", "E", "S", "W").contains(direction)) {
                throw new IllegalArgumentException();
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Could not parse direction from: " + line);
        }
        return direction;
    }

    private int[] getPosition(String line) {
        int xWidth, yWidth;

        try {
            String[] split = line.split(" ");

            xWidth = Integer.parseInt(split[0]);
            yWidth = Integer.parseInt(split[1]);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Could not parse position from: " + line);
        }

        return new int[]{xWidth, yWidth};
    }

    private static int[] move(int[] position, String direction) {
        int[] newPosition = position;

        if (direction.equals("N")) {
            newPosition[1] += +1;
        } else if (direction.equals("S")) {
            newPosition[1] += -1;
        } else if (direction.equals("E")) {
            newPosition[0] += +1;
        } else if (direction.equals("W")) {
            newPosition[0] += -1;
        }

        position = newPosition;
        return position;
    }

}
