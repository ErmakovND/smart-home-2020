package ru.sbt.mipt.oop.smarthome.events.providers;

import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.EventType;

public class RandomSensorEventProvider implements SensorEventProvider {
    @Override
    public SensorEvent getNextSensorEvent() {
        if (Math.random() < 0.05) return null;
        EventType sensorEventType = EventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
