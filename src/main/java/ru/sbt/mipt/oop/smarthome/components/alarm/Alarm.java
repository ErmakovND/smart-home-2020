package ru.sbt.mipt.oop.smarthome.components.alarm;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

public class Alarm implements Actionable {
    private AlarmState state;
    private String code;

    public Alarm() {
        this.state = new DeactivatedState(this);
    }

    public Alarm(AlarmState state) {
        this.state = state;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void alert() {
        state.alert();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AlarmState getState() {
        return state;
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    @Override
    public void execute(Action action) {
        action.applyTo(this);
    }
}
