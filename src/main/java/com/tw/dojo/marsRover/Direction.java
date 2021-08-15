package com.tw.dojo.marsRover;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    N(new Coordinate(0, 1)),
    S(new Coordinate(0, -1)),
    E(new Coordinate(1, 0)),
    W(new Coordinate(-1, 0));

    private final Coordinate updatedCoordinate;

    Direction(Coordinate updatedCoordinate) {
        this.updatedCoordinate = updatedCoordinate;
    }

    Direction turnRight() {
        List<Direction> all = Arrays.asList(N, E, S, W);
        return all.get((all.indexOf(this) + 1) % all.size());
    }

    Direction turnLeft() {
        List<Direction> all = Arrays.asList(N, E, S, W);
        return all.get((all.indexOf(this) + 3) % all.size());
    }

    public Coordinate updatedCoordinate() {
        return updatedCoordinate;
    }
}
