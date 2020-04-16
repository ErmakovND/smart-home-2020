package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;

public class AlertAlarmCommand implements Command {
    private final Alarm alarm;

    public AlertAlarmCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.alert();
    }
}
