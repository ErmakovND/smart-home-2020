package ru.sbt.mipt.oop;

import java.util.logging.Level;

public class DoorSensorEventHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public DoorSensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isDoorEvent(event)) return;

        smartHome.execute(o -> {
            if (!(o instanceof Room)) return;

            Room room = (Room) o;
            room.execute(obj -> {
                if (!(obj instanceof Door)) return;

                Door door = (Door) obj;
                if (!door.getId().equals(event.getObjectId())) return;

                boolean setDoorOpen = event.getType() == SensorEventType.DOOR_OPEN;
                door.setOpen(setDoorOpen);
                logEvent(door, setDoorOpen ? "opened" : "closed", room);
            });
        });
    }

    private void logEvent(Door door, String setDoor, Room room) {
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + setDoor + ".");
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_OPEN || event.getType() == SensorEventType.DOOR_CLOSED;
    }
}
