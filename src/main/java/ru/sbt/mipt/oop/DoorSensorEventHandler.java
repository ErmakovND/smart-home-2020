package ru.sbt.mipt.oop;

public class DoorSensorEventHandler implements SensorEventHandler {
    private final SmartHome smartHome;

    public DoorSensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isDoorEvent(event)) return;

        smartHome.execute(o -> {
            if (!(o instanceof Door)) return;

            Door door = (Door) o;
            if (!door.getId().equals(event.getObjectId())) return;

            boolean setDoorOpen = event.getType() == SensorEventType.DOOR_OPEN;
            door.setOpen(setDoorOpen);
            logEvent(door, setDoorOpen ? "opened" : "closed");
        });
    }

    private void logEvent(Door door, String setDoor) {
        System.out.println("Door " + door.getId() + " was " + setDoor + ".");
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_OPEN || event.getType() == SensorEventType.DOOR_CLOSED;
    }
}
