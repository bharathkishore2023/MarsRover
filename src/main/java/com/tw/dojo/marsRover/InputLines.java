package com.tw.dojo.marsRover;

public class InputLines {
    private String[] inputLines;

    public InputLines(String[] inputLines) {
        this.inputLines = inputLines;
    }

    int numberOfRovers() {
        return (inputLines.length - 1) / 2;
    }

    public String get(int positionLineIndex) {
        return inputLines[positionLineIndex];
    }
}
