package ru.sbt.mipt.oop.smarthome.events.handlers;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.EventType;
import ru.sbt.mipt.oop.smarthome.events.handlers.DoorStateEventHandler;

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
        smartHome = new SmartHome(new Alarm());
        smartHome.addRoom(new Room(new ArrayList<>(), doors,"room"));
    }

    @Test
    public void open() {
        SensorEvent event = new SensorEvent(EventType.DOOR_OPEN, "0");
        new DoorStateEventHandler(smartHome).handle(event);
        assertTrue(doors.get(0).isOpen());
        assertTrue(doors.get(1).isOpen());
    }

    @Test
    public void close() {
        SensorEvent event = new SensorEvent(EventType.DOOR_CLOSED, "1");
        new DoorStateEventHandler(smartHome).handle(event);
        assertFalse(doors.get(0).isOpen());
        assertFalse(doors.get(1).isOpen());
    }

    @Test
    public void notOpen() {
        SensorEvent event = new SensorEvent(EventType.DOOR_CLOSED, "0");
        new DoorStateEventHandler(smartHome).handle(event);
        assertFalse(doors.get(0).isOpen());
        assertTrue(doors.get(1).isOpen());
    }

    @Test
    public void notClose() {
        SensorEvent event = new SensorEvent(EventType.DOOR_OPEN, "1");
        new DoorStateEventHandler(smartHome).handle(event);
        assertFalse(doors.get(0).isOpen());
        assertTrue(doors.get(1).isOpen());
    }
}