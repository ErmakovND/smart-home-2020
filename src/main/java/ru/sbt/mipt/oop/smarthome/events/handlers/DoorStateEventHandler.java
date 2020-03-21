package ru.sbt.mipt.oop.smarthome.events.handlers;

import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.EventType;

public class DoorStateEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public DoorStateEventHandler(SmartHome smartHome) {
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

                boolean setDoorOpen = event.getType() == EventType.DOOR_OPEN;
                door.setOpen(setDoorOpen);
                logEvent(door, setDoorOpen ? "opened" : "closed", room);
            });
        });
    }

    private void logEvent(Door door, String setDoor, Room room) {
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + setDoor + ".");
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == EventType.DOOR_OPEN || event.getType() == EventType.DOOR_CLOSED;
    }
}
