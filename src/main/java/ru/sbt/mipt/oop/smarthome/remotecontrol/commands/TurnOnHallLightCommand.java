package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.Light;
import ru.sbt.mipt.oop.smarthome.components.Room;

public class TurnOnHallLightCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(o -> {
            if (!(o instanceof Room)) return;
            Room room = (Room) o;
            if (!(room.getName().equals("hall"))) return;
            room.execute(obj -> {
                if (!(obj instanceof Light)) return;
                Light light = (Light) obj;
                light.setOn(true);
            });
        });
    }
}
