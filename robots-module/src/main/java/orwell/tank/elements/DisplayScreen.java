package orwell.tank.elements;

import lejos.nxt.LCD;
import orwell.tank.inputs.Fire;

/**
 * Created by Michaël Ludmann on 6/12/15.
 */
public class DisplayScreen extends LCD {

    private static final int WAITING_LINE = 6;
    private static final int MOVE_LINE = 2;
    private static final int FIRE_LINE = 3;
    private static final int CONNECTED_LINE = 6;
    private static final int STOP_TANK_LINE = 5;
    private static final boolean IS_FIRE_INVERTED = false;
    private static final boolean IS_WAITING_INVERTED = true;
    private static final boolean IS_CONNECTED_INVERTED = true;
    private static final boolean IS_STOP_TANK_INVERTED = false;


    public void printWaitingForPC() {
        LCD.clear(WAITING_LINE);
        LCD.drawString(" Waiting for PC ", 0, WAITING_LINE, IS_WAITING_INVERTED);
    }

    public void printConnected() {
        LCD.clear(CONNECTED_LINE);
        LCD.drawString("Connected!", 0, CONNECTED_LINE, IS_CONNECTED_INVERTED);
    }

    public void printNotConnected() {
        LCD.clear(CONNECTED_LINE);
        LCD.drawString("Disconnected!", 0, CONNECTED_LINE, !IS_CONNECTED_INVERTED);
    }

    public void printAction(Fire fire) {
        LCD.clear(FIRE_LINE);
        LCD.drawString("Fire " + fire.hasLeftWeaponFired() + " "
                + fire.hasRightWeaponFired(), 0, FIRE_LINE, IS_FIRE_INVERTED);
    }

    public void printStopTank() {
        LCD.clear(STOP_TANK_LINE);
        LCD.drawString("TANK STOPPED", 0, STOP_TANK_LINE, IS_STOP_TANK_INVERTED);
    }
}
