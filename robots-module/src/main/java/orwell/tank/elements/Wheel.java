package orwell.tank.elements;

import lejos.nxt.NXTMotor;
import lejos.nxt.TachoMotorPort;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Wheel extends NXTMotor {

    public Wheel(TachoMotorPort port) {
        super(port);
    }
}
