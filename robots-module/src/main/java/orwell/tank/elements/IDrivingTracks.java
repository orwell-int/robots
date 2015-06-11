package orwell.tank.elements;

import lejos.nxt.NXTRegulatedMotor;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public interface IDrivingTracks {

    void setPower(double powerLeft, double powerRight);

    void stop();

    void simulateRecoil();
}
