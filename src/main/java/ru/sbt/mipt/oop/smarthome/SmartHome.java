package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.components.Room;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;
    Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm();
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.rooms = rooms;
        this.alarm = alarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute(Action action) {
        action.applyTo(this);
        alarm.execute(action);
        for (Actionable room : rooms) {
            room.execute(action);
        }
    }
}
