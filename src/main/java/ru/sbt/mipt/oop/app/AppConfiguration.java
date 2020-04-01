package ru.sbt.mipt.oop.app;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.events.decorators.IgnoringDecorator;
import ru.sbt.mipt.oop.smarthome.events.decorators.NotifyingDecorator;
import ru.sbt.mipt.oop.smarthome.events.handlers.*;
import ru.sbt.mipt.oop.smarthome.services.CommandSenderImpl;

@Configuration
public class AppConfiguration {
    @Bean
    Alarm alarm() {
        return new Alarm();
    }

    @Bean
    SmartHome smartHome() {
        return new SmartHome(alarm());
    }

    @Bean
    EventHandler alarmStateEventHandler() {
        return new EventHandlerAdapter(new NotifyingDecorator(new AlarmStateEventHandler(smartHome()), alarm()));
    }

    @Bean
    EventHandler doorStateEventHandler() {
        return new EventHandlerAdapter(new IgnoringDecorator(new DoorStateEventHandler(smartHome()), alarm()));
    }

    @Bean
    EventHandler lightStateEventHandler() {
        return new EventHandlerAdapter(new IgnoringDecorator(new LightStateEventHandler(smartHome()), alarm()));
    }

    @Bean
    EventHandler hallDoorEventHandler() {
        return new EventHandlerAdapter(new IgnoringDecorator(new HallDoorEventHandler(smartHome(), new CommandSenderImpl()), alarm()));
    }

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager manager = new SensorEventsManager();
        manager.registerEventHandler(hallDoorEventHandler());
        manager.registerEventHandler(lightStateEventHandler());
        manager.registerEventHandler(doorStateEventHandler());
        manager.registerEventHandler(alarmStateEventHandler());
        return manager;
    }
}
