package ru.sbt.mipt.oop;

import javafx.util.Pair;

public class LightSensorEventHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public LightSensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isLightEvent(event)) return;

        Room room = findRoom(event);
        Light light = findLight(event);
        if (room == null || light == null) return;

        boolean setLightOn = event.getType() == SensorEventType.LIGHT_ON;
        light.setOn(setLightOn);
        logEvent(room, light, setLightOn ? "on" : "off");
    }

    private void logEvent(Room room, Light light, String setLight) {
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned " + setLight + ".");
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == SensorEventType.LIGHT_ON || event.getType() == SensorEventType.LIGHT_OFF;
    }

    private Room findRoom(SensorEvent event) {
        return findRoomAndLight(event).getKey();
    }

    private Light findLight(SensorEvent event) {
        return findRoomAndLight(event).getValue();
    }

    private Pair<Room, Light> findRoomAndLight(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    return new Pair<>(room, light);
                }
            }
        }
        return new Pair<>(null, null);
    }
}
