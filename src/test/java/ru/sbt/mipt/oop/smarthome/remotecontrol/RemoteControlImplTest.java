package ru.sbt.mipt.oop.smarthome.remotecontrol;

import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.remotecontrol.commands.Command;

import static org.junit.Assert.assertEquals;

public class RemoteControlImplTest {

    @Test
    public void onButtonPressed() {
        RemoteControlImpl rc = new RemoteControlImpl();
        final int[] a = {0};
        rc.setCommand("A", () -> a[0]++);
        rc.onButtonPressed("A", "1");
        assertEquals(1, a[0]);
    }
}