package ru.sbt.mipt.oop.app;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.events.EventType;
import ru.sbt.mipt.oop.smarthome.events.decorators.IgnoringDecorator;
import ru.sbt.mipt.oop.smarthome.events.decorators.NotifyingDecorator;
import ru.sbt.mipt.oop.smarthome.events.handlers.*;
import ru.sbt.mipt.oop.smarthome.services.CommandSenderImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    Map<String, EventType> typeMap() {
        Map<String, EventType> map = new HashMap<>();
        map.put("LightIsOn", EventType.LIGHT_ON);
        map.put("LightIsOff", EventType.LIGHT_OFF);
        map.put("DoorIsOpen", EventType.DOOR_OPEN);
        map.put("DoorIsClosed", EventType.DOOR_CLOSED);
        return map;
    }

    @Bean
    EventHandler alarmStateEventHandler() {
        return new EventHandlerAdapter(new NotifyingDecorator(new AlarmStateEventHandler(smartHome()), alarm()), typeMap());
    }

    @Bean
    EventHandler doorStateEventHandler() {
        return new EventHandlerAdapter(new IgnoringDecorator(new DoorStateEventHandler(smartHome()), alarm()), typeMap());
    }

    @Bean
    EventHandler lightStateEventHandler() {
        return new EventHandlerAdapter(new IgnoringDecorator(new LightStateEventHandler(smartHome()), alarm()), typeMap());
    }

    @Bean
    EventHandler hallDoorEventHandler() {
        return new EventHandlerAdapter(new IgnoringDecorator(new HallDoorEventHandler(smartHome(), new CommandSenderImpl()), alarm()), typeMap());
    }

    @Bean
    SensorEventsManager sensorEventsManager(Collection<EventHandler> eventHandlers) {
        SensorEventsManager manager = new SensorEventsManager();
        for (EventHandler eventHandler : eventHandlers) {
            manager.registerEventHandler(eventHandler);
        }
        return manager;
    }
}
