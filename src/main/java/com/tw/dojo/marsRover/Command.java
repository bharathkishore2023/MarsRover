package com.tw.dojo.marsRover;

public interface Command {
    Position execute(Position position);
}