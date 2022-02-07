package com.tw.dojo.marsRover.command;

import com.tw.dojo.marsRover.model.Position;

public interface Command {
    Position execute(Position position);
}