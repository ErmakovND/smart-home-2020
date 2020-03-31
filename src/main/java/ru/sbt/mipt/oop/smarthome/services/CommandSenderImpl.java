package ru.sbt.mipt.oop.smarthome.services;

public class CommandSenderImpl implements CommandSender {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
