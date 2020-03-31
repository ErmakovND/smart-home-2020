package ru.sbt.mipt.oop.smarthome.events.handlers;

import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.EventType;
import ru.sbt.mipt.oop.smarthome.services.CommandSender;
import ru.sbt.mipt.oop.smarthome.services.CommandType;
import ru.sbt.mipt.oop.smarthome.services.SensorCommand;

public class HallDoorEventHandler implements EventHandler {
    private SmartHome smartHome;
    private CommandSender commandSender;

    public HallDoorEventHandler(SmartHome smartHome, CommandSender commandSender) {
        this.smartHome = smartHome;
        this.commandSender = commandSender;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!(event.getType() == EventType.DOOR_CLOSED)) return;

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
