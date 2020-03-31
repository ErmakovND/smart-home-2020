package ru.sbt.mipt.oop.smarthome.events;

public class AlarmEvent extends SensorEvent {
    private final String code;

    public AlarmEvent(EventType type, String objectId, String code) {
        super(type, objectId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "AlarmEvent{" +
                "type=" + getType() +
                ", code='" + code + '\'' +
                '}';
    }
}
