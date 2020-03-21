package ru.sbt.mipt.oop.smarthome.events.handlers;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.EventType;
import ru.sbt.mipt.oop.smarthome.events.handlers.LightStateEventHandler;

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
        SensorEvent event = new SensorEvent(EventType.LIGHT_ON, "0");
        new LightStateEventHandler(smartHome).handle(event);
        assertTrue(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
    }

    @Test
    public void turnOff() {
        SensorEvent event = new SensorEvent(EventType.LIGHT_OFF, "1");
        new LightStateEventHandler(smartHome).handle(event);
        assertFalse(lights.get(0).isOn());
        assertFalse(lights.get(1).isOn());
    }

    @Test
    public void notTurnOn() {
        SensorEvent event = new SensorEvent(EventType.LIGHT_OFF, "0");
        new LightStateEventHandler(smartHome).handle(event);
        assertFalse(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
    }

    @Test
    public void notTurnOff() {
        SensorEvent event = new SensorEvent(EventType.LIGHT_ON, "1");
        new LightStateEventHandler(smartHome).handle(event);
        assertFalse(lights.get(0).isOn());
        assertTrue(lights.get(1).isOn());
    }
}