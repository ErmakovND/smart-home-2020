package ru.sbt.mipt.oop.app;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

import org.springframework.context.support.AbstractApplicationContext;

public class Application {
    public static void main(String... args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
    }
}
