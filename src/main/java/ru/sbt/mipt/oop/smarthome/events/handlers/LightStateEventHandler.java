package ru.sbt.mipt.oop.smarthome.events.handlers;

import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.EventType;

public class LightStateEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public LightStateEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isLightEvent(event)) return;

        smartHome.execute(o -> {
            if (!(o instanceof Room)) return;

            Room room = (Room) o;
            room.execute(obj -> {
                if (!(obj instanceof Light)) return;

                Light light = (Light) obj;
                if (!light.getId().equals(event.getObjectId())) return;

                boolean setLightOn = event.getType() == EventType.LIGHT_ON;
                light.setOn(setLightOn);
                logEvent(light, setLightOn ? "on" : "off", room);
            });
        });
    }

    private void logEvent(Light light, String setLight, Room room) {
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned " + setLight + ".");
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == EventType.LIGHT_ON || event.getType() == EventType.LIGHT_OFF;
    }
}
