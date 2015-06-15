package orwell.tank.elements;

import lejos.nxt.NXTRegulatedMotor;


/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class DrivingTracksRegulated implements IDrivingTracks {
    private static final float SPEED_REDUC_FACTOR = 0.85f;
    public static final int RECOIL_ROTATION_DEGRES = 180;
    private final NXTRegulatedMotor leftMotor;
    private final NXTRegulatedMotor rightMotor;
    private DrivingTracksMemento drivingTracksMemento;
    private double powerLeft;
    private double powerRight;

    public DrivingTracksRegulated(NXTRegulatedMotor leftMotor, NXTRegulatedMotor rightMotor) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
    }

    @Override
    public void setPower(double powerLeft, double powerRight) {
        this.powerLeft = powerLeft;
        this.powerRight = powerRight;
        setPowerToMotor(leftMotor, (float) powerLeft);
        setPowerToMotor(rightMotor, (float) powerRight);
    }

    @Override
    public void stop() {
        leftMotor.flt(true);
        rightMotor.flt(true);
    }

    @Override
    public void simulateRecoil() {
        drivingTracksMemento = saveToMemento();
        leftMotor.setSpeed(leftMotor.getMaxSpeed());
        rightMotor.setSpeed(rightMotor.getMaxSpeed());
        leftMotor.rotate(RECOIL_ROTATION_DEGRES);
        rightMotor.rotate(RECOIL_ROTATION_DEGRES);
        restoreFromMemento(drivingTracksMemento);
    }

    private DrivingTracksMemento saveToMemento() {
        return new DrivingTracksMemento(this.powerLeft, this.powerRight);
    }

    private void restoreFromMemento(DrivingTracksMemento memento) {
        if (null != memento)
            setPower(memento.getSavedPowerLeft(), memento.getSavedPowerRight());
    }

    private void setPowerToMotor(NXTRegulatedMotor motor, float power) {
        motor.setSpeed(power * SPEED_REDUC_FACTOR  * motor.getMaxSpeed());
        if (0 < power)
            motor.backward();
        else if (0 > power)
            motor.forward();
        else
            motor.flt(true);
    }
}
