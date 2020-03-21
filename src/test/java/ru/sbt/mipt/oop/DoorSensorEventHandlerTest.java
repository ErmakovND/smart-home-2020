package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DoorSensorEventHandlerTest {
    private SmartHome smartHome;
    private List<Door> doors;

    @Before
    public void setUp() {
        doors = Arrays.asList(
                new Door(false, "0"),
                new Door(true, "1")
        );
        smartHome = new SmartHome();
        smartHome.addRoom(new Room(new ArrayList<>(), doors,"room"));
    }

    @Test
    public void open() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "0");
        new DoorSensorEventHandler(smartHome).handle(event);
        assertTrue(doors.get(0).isOpen());
        assertTrue(doors.get(1).isOpen());
    }

    @Test
    public void close() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        new DoorSensorEventHandler(smartHome).handle(event);
        assertFalse(doors.get(0).isOpen());
        assertFalse(doors.get(1).isOpen());
    }

    @Test
    public void notOpen() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "0");
        new DoorSensorEventHandler(smartHome).handle(event);
        assertFalse(doors.get(0).isOpen());
        assertTrue(doors.get(1).isOpen());
    }

    @Test
    public void notClose() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        new DoorSensorEventHandler(smartHome).handle(event);
        assertFalse(doors.get(0).isOpen());
        assertTrue(doors.get(1).isOpen());
    }
}