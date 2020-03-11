package ru.sbt.mipt.oop;

public class HallDoorSensorEventHandler implements SensorEventHandler, CommandSender {
    private SmartHome smartHome;

    public HallDoorSensorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!(event.getType() == SensorEventType.DOOR_CLOSED)) return;

        smartHome.execute(o -> {
            if (!(o instanceof Room)) return;

            Room room = (Room) o;
            if (!(room.getName().equals("hall"))) return;

            room.execute(obj -> {
                if (!(obj instanceof Light)) return;

                Light light = (Light) obj;
                if (!(light.getId().equals(event.getObjectId()))) return;

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
            sendCommand(command);
        });
    }
}
