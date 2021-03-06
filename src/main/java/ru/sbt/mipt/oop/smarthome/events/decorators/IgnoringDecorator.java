package ru.sbt.mipt.oop.smarthome.events.decorators;

import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.components.alarm.AlertedState;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler;

public class IgnoringDecorator implements EventHandler {
    private final EventHandler assignee;
    private final Alarm alarm;

    public IgnoringDecorator(EventHandler assignee, Alarm alarm) {
        this.assignee = assignee;
        this.alarm = alarm;
    }

    @Override
    public void handle(SensorEvent event) {
        if (alarm.getState() instanceof AlertedState) {
            return;
        }
        assignee.handle(event);
    }
}
