package ru.sbt.mipt.oop.smarthome.events.decorators;

import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler;

public class BaseHandlerDecorator implements EventHandler {
    final EventHandler assignee;

    public BaseHandlerDecorator(EventHandler assignee) {
        this.assignee = assignee;
    }

    @Override
    public void handle(SensorEvent event) {
        assignee.handle(event);
    }
}
