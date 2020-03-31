package ru.sbt.mipt.oop.smarthome.events.handlers;

import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.events.AlarmEvent;
import ru.sbt.mipt.oop.smarthome.events.EventType;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;

public class AlarmStateEventHandler implements EventHandler {
    private final SmartHome smartHome;

    public AlarmStateEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if (!isAlarmEvent(event)) return;

        smartHome.execute(o -> {
            if (!(o instanceof Alarm)) return;

            Alarm alarm = (Alarm) o;
            if (event.getType() == EventType.ALARM_ACTIVATE) {
                alarm.activate(((AlarmEvent) event).getCode());
            } else {
                alarm.deactivate(((AlarmEvent) event).getCode());
            }
        });
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return event instanceof AlarmEvent;
    }
}
