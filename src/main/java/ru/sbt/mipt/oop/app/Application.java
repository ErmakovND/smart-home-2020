package ru.sbt.mipt.oop.app;

import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.decorators.IgnoringDecorator;
import ru.sbt.mipt.oop.smarthome.events.decorators.NotifyingDecorator;
import ru.sbt.mipt.oop.smarthome.events.handlers.*;
import ru.sbt.mipt.oop.smarthome.events.providers.RandomSensorEventProvider;
import ru.sbt.mipt.oop.smarthome.services.CommandSender;
import ru.sbt.mipt.oop.smarthome.services.CommandSenderImpl;
import ru.sbt.mipt.oop.smarthome.services.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthome.services.SensorEventManager;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {
        Alarm alarm = new Alarm();
        SmartHome smartHome = new SmartHome(alarm);
        CommandSender commandSender = new CommandSenderImpl();
        List<EventHandler> eventHandlers = Arrays.asList(
                new IgnoringDecorator(new LightStateEventHandler(smartHome), alarm),
                new IgnoringDecorator(new DoorStateEventHandler(smartHome), alarm),
                new IgnoringDecorator(new HallDoorEventHandler(smartHome, commandSender), alarm),
                new NotifyingDecorator(new AlarmStateEventHandler(smartHome), alarm));
        SensorEventManager sensorEventManager = new SensorEventManager(new RandomSensorEventProvider(), eventHandlers);
        sensorEventManager.startHandling();
    }
}
