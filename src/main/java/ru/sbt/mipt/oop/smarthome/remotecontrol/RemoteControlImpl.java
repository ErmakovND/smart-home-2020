package ru.sbt.mipt.oop.smarthome.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
    private final Map<String, Command> commandMap;

    public RemoteControlImpl() {
        commandMap = new HashMap<>();
    }

    public void setCommand(String buttonCode, Command command) {
        commandMap.put(buttonCode, command);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        Command command = commandMap.get(buttonCode);
        if (command != null) {
            command.execute();
        }
    }
}
