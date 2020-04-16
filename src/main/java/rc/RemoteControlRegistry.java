package rc;

public class RemoteControlRegistry {

    /**
     * Register remote control with id rcId.
     * When button on a real remote control device is pressed this library will call remoteControl.onButtonPressed(...).
     * @param remoteControl
     * @param rcId
     */
    public void registerRemoteControl(RemoteControl remoteControl, String rcId) {
        // here goes some library code which registers our remote control with given ID (rcId)
    }

}
