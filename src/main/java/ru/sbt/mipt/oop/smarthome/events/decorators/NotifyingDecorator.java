package ru.sbt.mipt.oop.smarthome.events.decorators;

import ru.sbt.mipt.oop.smarthome.components.alarm.Alarm;
import ru.sbt.mipt.oop.smarthome.components.alarm.AlertedState;
import ru.sbt.mipt.oop.smarthome.events.SensorEvent;
import ru.sbt.mipt.oop.smarthome.events.handlers.EventHandler;

public class NotifyingDecorator extends BaseHandlerDecorator {
    private final Alarm alarm;

    public NotifyingDecorator(EventHandler assignee, Alarm alarm) {
        super(assignee);
        this.alarm = alarm;
    }

    @Override
    public void handle(SensorEvent event) {
        assignee.handle(event);
        if (alarm.getState() instanceof AlertedState) {
            System.out.println("Sending sms");
        }
    }
}
