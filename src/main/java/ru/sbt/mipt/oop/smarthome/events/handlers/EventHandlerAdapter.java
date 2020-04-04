package ru.sbt.mipt.oop.smarthome.events.handlers;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.smarthome.events.EventType;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;

import java.util.Map;

public class EventHandlerAdapter implements EventHandler {
    private final ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler handler;
    private final Map<String, EventType> typeMap;

    public EventHandlerAdapter(ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler handler, Map<String, EventType> typeMap) {
        this.handler = handler;
        this.typeMap = typeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        handler.handle(adaptEvent(event));
    }

    private SensorEvent adaptEvent(CCSensorEvent event) {
        return new SensorEvent(typeMap.get(event.getEventType()), event.getObjectId());
    }
}
