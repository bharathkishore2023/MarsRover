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
}
