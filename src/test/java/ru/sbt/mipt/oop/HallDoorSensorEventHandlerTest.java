package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HallDoorSensorEventHandlerTest {
    private SmartHome smartHome;
    private List<Light> lights;

    @Before
    public void setUp() {
        List<Light> hallLights = Arrays.asList(
                new Light("0", false),
                new Light("1", true)
        );
        List<Light> roomLights = Arrays.asList(
                new Light("00", false),
                new Light("01", true)
        );
        List<Door> hallDoors = Arrays.asList(
                new Door(false, "0"),
                new Door(true, "1")
        );
        List<Door> roomDoors = Arrays.asList(
                new Door(false, "00"),
                new Door(true, "01")
        );
        Room hall = new Room(
                hallLights,
                hallDoors,
                "hall"
        );
        Room room = new Room(
                roomLights,
                roomDoors,
                "room"
        );
        smartHome = new SmartHome(Arrays.asList(hall, room));
        lights = new ArrayList<>(hallLights);
        lights.addAll(roomLights);
    }

    @Test
    public void closeHallDoorTurnOffLight() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        new HallDoorSensorEventHandler(smartHome).handle(event);
        for (Light light : lights) {
            assertFalse(light.isOn());
        }
    }

    @Test
    public void closeRoomDoorLeaveLight() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "01");
        List<Light> oldLights = new ArrayList<>(lights);
        new HallDoorSensorEventHandler(smartHome).handle(event);
        for (int i = 0; i < lights.size(); ++i) {
            assertEquals(oldLights.get(i).isOn(), lights.get(i).isOn());
        }
    }
}