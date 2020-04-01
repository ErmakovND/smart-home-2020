package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.smarthome.components.alarm.ActivatedState;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.components.alarm.AlertedState;

import static org.junit.Assert.*;

public class AlertAlarmCommandTest {

    @Test
    public void execute() {
        Alarm alarm = new Alarm();
        Command command = new AlertAlarmCommand(alarm);
        command.execute();
        assertFalse(alarm.getState() instanceof AlertedState);
        alarm.setState(new ActivatedState(alarm, "code"));
        command.execute();
        assertTrue(alarm.getState() instanceof AlertedState);
    }
}