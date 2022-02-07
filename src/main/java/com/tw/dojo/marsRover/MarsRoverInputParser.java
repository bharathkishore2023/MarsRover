package com.tw.dojo.marsRover;

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
}
