package ru.sbt.mipt.oop.smarthome.remotecontrol;

import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RemoteControlImplTest {

    @Test
    public void onButtonPressed() {
        final int[] a = {0};
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("A", () -> a[0]++);
        RemoteControlImpl rc = new RemoteControlImpl(commandMap);
        rc.onButtonPressed("A", "1");
        assertEquals(1, a[0]);
    }
}