package ru.sbt.mipt.oop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HallDoorSensorEventHandlerTest {
    private SmartHome smartHome;
    private CommandSender commandSender;
    private List<Light> lights;

    @Before
    public void setUp() {
        List<Light> hallLights = Arrays.asList(
                new Light("hl0", false),
                new Light("hl1", true)
        );
        List<Light> roomLights = Arrays.asList(
                new Light("rl0", false),
                new Light("rl1", true)
        );
        List<Door> hallDoors = Arrays.asList(
                new Door(false, "hd0"),
                new Door(true, "hd1")
        );
        List<Door> roomDoors = Arrays.asList(
                new Door(false, "rd0"),
                new Door(true, "rd1")
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
        commandSender = new CommandSenderImpl();
        lights = new ArrayList<>(hallLights);
        lights.addAll(roomLights);
    }

    @Test
    public void closeHallDoorTurnOffLight() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "hd1");
        new HallDoorSensorEventHandler(smartHome, commandSender).handle(event);
        for (Light light : lights) {
            assertFalse(light.isOn());
        }
    }

    @Test
    public void closeRoomDoorLeaveLight() {
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "rd1");
        List<Light> oldLights = new ArrayList<>(lights);
        new HallDoorSensorEventHandler(smartHome, commandSender).handle(event);
        for (int i = 0; i < lights.size(); ++i) {
            assertEquals(oldLights.get(i).isOn(), lights.get(i).isOn());
        }
    }
}