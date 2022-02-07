package com.tw.dojo.marsRover.command;

import com.tw.dojo.marsRover.model.Position;

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
