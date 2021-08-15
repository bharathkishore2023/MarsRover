package com.tw.dojo.marsRover;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    N, S, E, W;

    Direction turnRight() {
        List<Direction> all = Arrays.asList(N, E, S, W);
        return all.get((all.indexOf(this) + 1) % all.size());
    }

    Direction turnLeft() {
        List<Direction> all = Arrays.asList(N, E, S, W);
        return all.get((all.indexOf(this) + 3) % all.size());
    }
}
