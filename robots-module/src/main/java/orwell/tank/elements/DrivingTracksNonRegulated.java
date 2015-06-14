package orwell.tank.elements;

import lejos.nxt.Battery;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;

import static java.lang.Math.abs;


/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class DrivingTracksNonRegulated implements IDrivingTracks {
    private final NXTMotor leftMotor;
    private final NXTMotor rightMotor;

    public DrivingTracksNonRegulated(TachoMotorPort motorPortLeft, TachoMotorPort motorPortRight) {
        leftMotor = new NXTMotor(motorPortLeft);
        rightMotor = new NXTMotor(motorPortRight);
    }

    @Override
    public void setPower(double powerLeft, double powerRight) {
        setPowerToMotor(leftMotor, powerLeft);
        setPowerToMotor(rightMotor, powerLeft);
    }

    @Override
    public void stop() {
        leftMotor.flt();
        rightMotor.flt();
    }

    @Override
    public void simulateRecoil() {
        
    }

    private void setPowerToMotor(NXTMotor motor, double power) {
        motor.setPower((int) abs(power));
        if (0 < power)
            motor.backward();
        else if (0 > power)
            motor.forward();
        else
            motor.flt();
    }
}
