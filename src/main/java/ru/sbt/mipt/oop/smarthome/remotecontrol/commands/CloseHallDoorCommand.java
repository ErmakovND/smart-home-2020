package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.Door;
import ru.sbt.mipt.oop.smarthome.components.Room;

public class CloseHallDoorCommand implements Command {
    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(o -> {
            if (!(o instanceof Room)) return;
            Room room = (Room) o;
            if (!(room.getName().equals("hall"))) return;
            room.execute(obj -> {
                if (!(obj instanceof Door)) return;
                Door door = (Door) obj;
                door.setOpen(false);
            });
        });
    }
}
