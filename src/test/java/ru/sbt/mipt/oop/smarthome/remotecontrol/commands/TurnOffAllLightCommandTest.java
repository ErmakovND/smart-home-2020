package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TurnOffAllLightCommandTest {
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
        Command command = new TurnOffAllLightCommand(smartHome);
        command.execute();
        for (Light roomLight : roomLights) {
            assertFalse(roomLight.isOn());
        }
        for (Light hallLight : hallLights) {
            assertFalse(hallLight.isOn());
        }
    }
}