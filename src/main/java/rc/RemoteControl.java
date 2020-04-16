package rc;

public interface RemoteControl {
    /**
     * This method will be called when a button buttonCode is pressed on a remote control with id rcId
     * @param buttonCode button letter: “A”, “B”, “C”, “D”, “1”, “2”, “3”, “4”
     * @param rcId remote control id
     */
    void onButtonPressed(String buttonCode, String rcId);
}
