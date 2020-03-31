package ru.sbt.mipt.oop.smarthome.components.alarm;

public class AlertedState implements AlarmState {
    private Alarm alarm;
    private String code;

    public AlertedState(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(this.code)) {
            alarm.setState(new DeactivatedState(alarm));
        }
    }

    @Override
    public void alert() {
    }
}
