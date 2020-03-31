package ru.sbt.mipt.oop.smarthome.events.providers;

import ru.sbt.mipt.oop.smarthome.events.SensorEvent;

public interface SensorEventProvider {
    SensorEvent getNextSensorEvent();
}
