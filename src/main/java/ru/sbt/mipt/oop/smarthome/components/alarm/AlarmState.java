package ru.sbt.mipt.oop.smarthome.components.alarm;

abstract class AlarmState {
    Alarm alarm;

    AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    abstract void activate(String code);
    abstract void deactivate(String code);
    abstract void alert();
}
