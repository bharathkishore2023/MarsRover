package com.tw.dojo.marsRover.model;

import java.util.Objects;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Coordinate update(Direction direction) {
        Coordinate updatedCoordinate = direction.updatedCoordinate();
        return new Coordinate(this.x + updatedCoordinate.x, this.y + updatedCoordinate.y);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
