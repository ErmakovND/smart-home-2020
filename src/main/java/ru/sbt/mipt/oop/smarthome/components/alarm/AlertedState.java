package ru.sbt.mipt.oop.smarthome.components.alarm;

public class AlertedState extends AlarmState {
    public AlertedState(Alarm alarm) {
        super(alarm);
    }

    @Override
    void activate(String code) {
    }

    @Override
    void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.setState(new DeactivatedState(alarm));
        }
    }

    @Override
    void alert() {
    }
}
