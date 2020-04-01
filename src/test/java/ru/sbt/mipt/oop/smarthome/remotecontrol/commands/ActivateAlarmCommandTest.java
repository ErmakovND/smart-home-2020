package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.components.alarm.ActivatedState;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;

import static org.junit.Assert.*;

public class ActivateAlarmCommandTest {

    @Test
    public void execute() {
        Alarm alarm = new Alarm();
        Command command = new ActivateAlarmCommand(alarm, "code");
        command.execute();
        assertTrue(alarm.getState() instanceof ActivatedState);
    }

}