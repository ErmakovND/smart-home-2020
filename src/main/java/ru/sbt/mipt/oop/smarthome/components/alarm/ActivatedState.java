package ru.sbt.mipt.oop.smarthome.components.alarm;

public class ActivatedState implements AlarmState {
    private Alarm alarm;
    private String code;

    public ActivatedState(Alarm alarm, String code) {
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
        } else {
            alarm.setState(new AlertedState(alarm, code));
        }
    }

    @Override
    public void alert() {
        alarm.setState(new AlertedState(alarm, code));
    }
}
