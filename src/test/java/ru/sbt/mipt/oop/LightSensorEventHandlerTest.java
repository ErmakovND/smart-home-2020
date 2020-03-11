package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LightSensorEventHandlerTest {
    private SmartHome smartHome;
    private List<Light> lights;

    @Before
    public void setUp() {
        lights = Arrays.asList(
                new Light("0", false),
                new Light("1", true)
        );
        smartHome = new SmartHome();
        smartHome.addRoom(new Room(lights, new ArrayList<>(), "room"));
    }

    @Test
    public void turnOn() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "0");
        new LightSensorEventHandler(smartHome).handle(event);
        assertTrue(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
    }

    @Test
    public void turnOff() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        new LightSensorEventHandler(smartHome).handle(event);
        assertFalse(lights.get(0).isOn());
        assertFalse(lights.get(1).isOn());
    }

    @Test
    public void notTurnOn() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "0");
        new LightSensorEventHandler(smartHome).handle(event);
        assertFalse(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
    }

    @Test
    public void notTurnOff() {
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        new LightSensorEventHandler(smartHome).handle(event);
        assertFalse(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
    }
}