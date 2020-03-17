package ru.sbt.mipt.oop;

public class HallDoorSensorEventHandler implements SensorEventHandler {
    private SmartHome smartHome;
    private CommandSender commandSender;

    public HallDoorSensorEventHandler(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!(event.getType() == SensorEventType.DOOR_CLOSED)) return;

        smartHome.execute(o -> {
            if (!(o instanceof Room)) return;

            Room room = (Room) o;
            if (!(room.getName().equals("hall"))) return;

            room.execute(obj -> {
                if (!(obj instanceof Door)) return;

                Door door = (Door) obj;
                if (!(door.getId().equals(event.getObjectId()))) return;

                turnOffAllLights();
            });
        });
    }

    private void turnOffAllLights() {
        smartHome.execute(o -> {
            if (!(o instanceof Light)) return;

            Light light = (Light) o;
            light.setOn(false);
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(command);
        });
    }
}
