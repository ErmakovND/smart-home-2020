package ru.sbt.mipt.oop.smarthome.components.alarm;

public class DeactivatedState extends AlarmState {
    public DeactivatedState(Alarm alarm) {
        super(alarm);
    }

    @Override
    void activate(String code) {
        alarm.setCode(code);
        alarm.setState(new ActivatedState(alarm));
    }

    @Override
    void deactivate(String code) {
    }

    @Override
    void alert() {
        alarm.setState(new AlertedState(alarm));
    }
}
