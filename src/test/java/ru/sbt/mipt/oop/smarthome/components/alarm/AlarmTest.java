package ru.sbt.mipt.oop.smarthome.components.alarm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlarmTest {
    private Alarm alarm;

    @Before
    public void setUp() {
        alarm = new Alarm();
    }

    @Test
    public void activateWhenDeactivated() {
        alarm.activate("code");
        assertTrue(alarm.getState() instanceof ActivatedState);
    }

    @Test
    public void deactivateWhenActivatedWithSuccess() {
        alarm.setState(new ActivatedState(alarm, "code"));
        alarm.deactivate("code");
        assertTrue(alarm.getState() instanceof DeactivatedState);
    }

    @Test
    public void deactivateWhenActivatedWithFail() {
        alarm.setState(new ActivatedState(alarm, "code"));
        alarm.deactivate("another code");
        assertTrue(alarm.getState() instanceof AlertedState);
    }

    @Test
    public void deactivateWhenAlertedWithSuccess() {
        alarm.setState(new AlertedState(alarm, "code"));
        alarm.deactivate("code");
        assertTrue(alarm.getState() instanceof DeactivatedState);
    }

    @Test
    public void deactivateWhenAlertedWithFail() {
        alarm.setState(new AlertedState(alarm, "code"));
        alarm.deactivate("another code");
        assertTrue(alarm.getState() instanceof AlertedState);
    }

    @Test
    public void alertWhenActivated() {
        alarm.setState(new ActivatedState(alarm, "code"));
        alarm.alert();
        assertTrue(alarm.getState() instanceof AlertedState);
    }

    @Test
    public void alertWhenDeactivated() {
        alarm.setState(new DeactivatedState(alarm));
        alarm.alert();
        assertTrue(alarm.getState() instanceof DeactivatedState);
    }
}