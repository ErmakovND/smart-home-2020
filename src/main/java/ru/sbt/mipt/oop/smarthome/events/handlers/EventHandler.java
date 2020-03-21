package ru.sbt.mipt.oop.smarthome.events.handlers;

import ru.sbt.mipt.oop.smarthome.events.SensorEvent;

public interface EventHandler {
    void handle(SensorEvent event);
}
