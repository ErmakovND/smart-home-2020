package ru.sbt.mipt.oop.smarthome.remotecontrol.commands;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.components.Light;

public class TurnOffAllLightCommand implements Command {
    private SmartHome smartHome;

    public TurnOffAllLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(o -> {
            if(!(o instanceof Light)) return;
            Light light = (Light) o;
            light.setOn(false);
        });
    }
}
