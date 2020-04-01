package ru.sbt.mipt.oop.smarthome.events.handlers;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.EventType;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;

import java.util.HashMap;
import java.util.Map;

public class EventHandlerAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler handler;
    private final Map<String, EventType> typeMap;

    public EventHandlerAdapter(ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler handler) {
        this.handler = handler;
        typeMap = new HashMap<>();
        typeMap.put("LightIsOn", EventType.LIGHT_ON);
        typeMap.put("LightIsOff", EventType.LIGHT_OFF);
        typeMap.put("DoorIsOpen", EventType.DOOR_OPEN);
        typeMap.put("DoorIsClosed", EventType.DOOR_CLOSED);
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        handler.handle(adaptEvent(event));
    }

    private SensorEvent adaptEvent(CCSensorEvent event) {
        return new SensorEvent(typeMap.get(event.getEventType()), event.getObjectId());
    }
}
