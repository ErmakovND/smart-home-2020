package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.services.CommandSenderImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TurnOnHallLightCommandTest {
    @Test
    public void execute() {
        List<Light> hallLights = Arrays.asList(
                new Light("hl0", false),
                new Light("hl1", true)
        );
        List<Light> roomLights = Arrays.asList(
                new Light("rl0", false),
                new Light("rl1", true)
        );
        Room hall = new Room(
                hallLights,
                new ArrayList<>(),
                "hall"
        );
        Room room = new Room(
                roomLights,
                new ArrayList<>(),
                "room"
        );
        SmartHome smartHome = new SmartHome(Arrays.asList(hall, room), new Alarm());
        Command command = new TurnOnHallLightCommand(smartHome);
        List<Light> roomLightsBackup = new ArrayList<>(roomLights);
        command.execute();
        for (Light hallLight : hallLights) {
            assertTrue(hallLight.isOn());
        }
        for (int i = 0; i < roomLights.size(); i++) {
             assertEquals(roomLightsBackup.get(i).isOn(), roomLights.get(i).isOn());
        }
    }
}