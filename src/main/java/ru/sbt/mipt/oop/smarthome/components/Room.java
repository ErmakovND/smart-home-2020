package ru.sbt.mipt.oop.smarthome.components;

import ru.sbt.mipt.oop.smarthome.Action;
import ru.sbt.mipt.oop.smarthome.Actionable;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.applyTo(this);
        for (Actionable light : lights) {
            light.execute(action);
        }
        for (Actionable door : doors) {
            door.execute(action);
        }
    }
}
