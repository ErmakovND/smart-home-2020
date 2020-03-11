package ru.sbt.mipt.oop;

interface CommandSender {
    default void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
