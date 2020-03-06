package ru.sbt.mipt.oop;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {
        SmartHome smartHome = new JsonSmartHomeReader("smart-home-1.js").readSmartHome();
        List<SensorEventHandler> eventHandlers = Arrays.asList(
                new LightSensorEventHandler(smartHome),
                new DoorSensorEventHandler(smartHome));
        SensorEventManager sensorEventManager = new SensorEventManager(new RandomSensorEventProvider(), eventHandlers);
        sensorEventManager.startHandling();
    }
}
