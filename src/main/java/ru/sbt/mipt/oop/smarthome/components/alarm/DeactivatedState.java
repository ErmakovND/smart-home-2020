package ru.sbt.mipt.oop.smarthome.components.alarm;

public class DeactivatedState implements AlarmState {
    private Alarm alarm;

    public DeactivatedState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        alarm.setState(new ActivatedState(alarm, code));
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void alert() {
    }
}
