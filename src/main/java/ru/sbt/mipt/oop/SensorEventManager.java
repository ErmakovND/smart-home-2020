package ru.sbt.mipt.oop;

import java.util.List;

public class SensorEventManager {
    private SensorEventProvider sensorEventProvider;
    private List<SensorEventHandler> handlers;

    public SensorEventManager(SensorEventProvider sensorEventProvider, List<SensorEventHandler> handlers) {
        this.sensorEventProvider = sensorEventProvider;
        this.handlers = handlers;
    }
    
    public void startHandling() {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (SensorEventHandler sensorEventHandler : handlers) {
                sensorEventHandler.handle(event);
            }
            event = sensorEventProvider.getNextSensorEvent();
        }
    }
}
