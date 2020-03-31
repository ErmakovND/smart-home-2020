package ru.sbt.mipt.oop.smarthome.components.alarm;

interface AlarmState {

    void activate(String code);
    void deactivate(String code);
    void alert();

}
