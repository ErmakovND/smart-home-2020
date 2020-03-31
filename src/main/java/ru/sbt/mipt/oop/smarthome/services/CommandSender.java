package ru.sbt.mipt.oop.smarthome.services;

public interface CommandSender {
    void sendCommand(SensorCommand command);
}
