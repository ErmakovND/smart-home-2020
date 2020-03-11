package ru.sbt.mipt.oop;

public class LightSensorController implements SensorEventHandler, CommandSender {
    private SmartHome smartHome;

    public LightSensorController(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (isHallDoorClosed(event)) {
            turnOffAllLights();
        }
    }

    private void turnOffAllLights() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                turnOffLight(light);
            }
        }
    }

    private void turnOffLight(Light light) {
        light.setOn(false);
        SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
        sendCommand(command);
    }

    private boolean isHallDoorClosed(SensorEvent event) {
        Room room = findRoomByDoor(event);
        return room != null && event.getType() == SensorEventType.DOOR_CLOSED && room.getName().equals("hall");
    }

    private Room findRoomByDoor(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    return room;
                }
            }
        }
        return null;
    }
}
