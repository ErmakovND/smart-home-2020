package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.services.CommandSender;
import ru.sbt.mipt.oop.smarthome.services.CommandSenderImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CloseHallDoorCommandTest {
    @Test
    public void execute() {
        List<Door> hallDoors = Arrays.asList(
                new Door(false, "hd0"),
                new Door(true, "hd1")
        );
        List<Door> roomDoors = Arrays.asList(
                new Door(false, "rd0"),
                new Door(true, "rd1")
        );
        Room hall = new Room(
                new ArrayList<>(),
                hallDoors,
                "hall"
        );
        Room room = new Room(
                new ArrayList<>(),
                roomDoors,
                "room"
        );
        SmartHome smartHome = new SmartHome(Arrays.asList(hall, room), new Alarm());
        Command command = new CloseHallDoorCommand(smartHome);
        List<Door> roomDoorsBackup = new ArrayList<>(roomDoors);
        command.execute();
        for (Door hallDoor : hallDoors) {
            assertFalse(hallDoor.isOpen());
        }
        for (int i = 0; i < roomDoorsBackup.size(); i++) {
            assertEquals(roomDoorsBackup.get(i).isOpen(), roomDoors.get(i).isOpen());
        }
    }
}