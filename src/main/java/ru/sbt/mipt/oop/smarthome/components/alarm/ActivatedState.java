package ru.sbt.mipt.oop.smarthome.components.alarm;

public class ActivatedState extends AlarmState {
    public ActivatedState(Alarm alarm) {
        super(alarm);
    }

    @Override
    void activate(String code) {
    }

    @Override
    void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.setState(new DeactivatedState(alarm));
        } else {
            alarm.setState(new AlertedState(alarm));
        }
    }

    @Override
    void alert() {
        alarm.setState(new AlertedState(alarm));
    }
}
