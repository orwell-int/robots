package orwell.tank.elements;

import lejos.nxt.LCD;
import orwell.tank.actions.Fire;

/**
 * Created by Michaël Ludmann on 6/12/15.
 */
public class DisplayScreen extends LCD {

    private static final int WAITING_LINE = 6;
    private static final int MOVE_LINE = 2;
    private static final int FIRE_LINE = 3;
    private static final int CONNECTED_LINE = 5;
    private static final boolean IS_FIRE_INVERTED = false;
    private static final boolean IS_WAITING_INVERTED = true;
    private static final boolean IS_CONNECTED_INVERTED = true;


    public static void printWaitingForPC() {
        LCD.clear(WAITING_LINE);
        LCD.drawString(" Waiting for PC ", 0, WAITING_LINE, IS_WAITING_INVERTED);
    }

    public static void printConnected() {
        LCD.clear(CONNECTED_LINE);
        LCD.drawString("Connected!", 0, CONNECTED_LINE, IS_CONNECTED_INVERTED);
    }

    public void printAction(Fire fire) {
        this.clear(FIRE_LINE);
        this.drawString("Fire " + fire.hasLeftWeaponFired() + " "
                + fire.hasRightWeaponFired(), 0, FIRE_LINE, IS_FIRE_INVERTED);
    }
}
