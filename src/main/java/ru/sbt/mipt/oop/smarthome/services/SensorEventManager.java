package ru.sbt.mipt.oop.smarthome.services;

import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.providers.SensorEventProvider;
import ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler;

import java.util.List;

public class SensorEventManager {
    private SensorEventProvider sensorEventProvider;
    private List<EventHandler> handlers;

    public SensorEventManager(SensorEventProvider sensorEventProvider, List<EventHandler> handlers) {
        this.sensorEventProvider = sensorEventProvider;
        this.handlers = handlers;
    }
    
    public void startHandling() {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventHandler sensorEventHandler : handlers) {
                sensorEventHandler.handle(event);
            }
            event = sensorEventProvider.getNextSensorEvent();
        }
    }
}
