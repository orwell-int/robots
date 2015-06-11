package orwell.tank.elements;

import lejos.nxt.Battery;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;


/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class DrivingTracksRegulated implements IDrivingTracks {
    private static final float SPEED_REDUC_FACTOR = 0.85f;
    private final NXTRegulatedMotor leftMotor;
    private final NXTRegulatedMotor rightMotor;
    private DrivingTracksMemento drivingTracksMemento;
    private double powerLeft;
    private double powerRight;

    public DrivingTracksRegulated(TachoMotorPort motorPortLeft, TachoMotorPort motorPortRight) {
        leftMotor = new NXTRegulatedMotor(motorPortLeft);
        rightMotor = new NXTRegulatedMotor(motorPortRight);
    }

    @Override
    public void setPower(double powerLeft, double powerRight) {
        this.powerLeft = powerLeft;
        this.powerRight = powerRight;
        float batteryVoltage = Battery.getVoltage();
        setPowerToMotor(leftMotor, (float) powerLeft * batteryVoltage);
        setPowerToMotor(rightMotor, (float) powerRight * batteryVoltage);
    }

    @Override
    public void stop() {
        leftMotor.flt(true);
        rightMotor.flt(true);
    }

    @Override
    public void simulateRecoil() {
        drivingTracksMemento = saveToMemento();

        leftMotor.rotate(180);
        rightMotor.rotate(180);
        restoreFromMememto(drivingTracksMemento);
    }

    private DrivingTracksMemento saveToMemento() {
        return new DrivingTracksMemento(this.powerLeft, this.powerRight);
    }

    private void restoreFromMememto(DrivingTracksMemento memento) {
        if (null != memento)
            setPower(memento.getSavedPowerLeft(), memento.getSavedPowerRight());
    }

    private void setPowerToMotor(NXTRegulatedMotor motor, float power) {
        motor.setSpeed(power * SPEED_REDUC_FACTOR);
        if (0 < power)
            motor.backward();
        else if (0 > power)
            motor.forward();
        else
            motor.flt(true);
    }
}
