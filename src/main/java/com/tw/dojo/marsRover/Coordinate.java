package com.tw.dojo.marsRover;

import java.util.Objects;

public class Coordinate {
    int x;
    int y;

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
        if (direction.equals(Direction.S)) {
            return new Coordinate(this.x, this.y - 1);
        } else if (direction.equals(Direction.E)) {
            return new Coordinate(this.x + 1, this.y);
        } else if (direction.equals(Direction.W)) {
            return new Coordinate(this.x - 1, this.y);
        }
        return new Coordinate(this.x, this.y + 1);
    }
}
