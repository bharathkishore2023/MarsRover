package com.tw.dojo.marsRover;

import java.util.List;

public class Commands {

    private final List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = commands;
    }

    public Position execute(Position originalPosition) {
        Position position = null;
        for (Command command : commands) {
            position = command.execute(originalPosition);
            originalPosition = position;
        }
        return position;
    }
}
