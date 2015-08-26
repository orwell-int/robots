package orwell.tank.elements;

import lejos.nxt.LCD;
import lejos.nxt.NXTRegulatedMotor;


/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class DrivingTracksRegulated implements IDrivingTracks {
    public static final int RECOIL_ROTATION_DEGREES = 180;
    private static final float SPEED_REDUCE_FACTOR = 0.85f;
    private final NXTRegulatedMotor leftMotor;
    private final NXTRegulatedMotor rightMotor;
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
        leftMotor.stop();
        rightMotor.stop();
    }

    @Override
    public void simulateRecoil() {
        DrivingTracksMemento drivingTracksMemento = saveToMemento();
        leftMotor.setSpeed(leftMotor.getMaxSpeed());
        rightMotor.setSpeed(rightMotor.getMaxSpeed());
        leftMotor.rotate(RECOIL_ROTATION_DEGREES);
        rightMotor.rotate(RECOIL_ROTATION_DEGREES);
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
        motor.setSpeed(power * SPEED_REDUCE_FACTOR * motor.getMaxSpeed());
        if (0 < power)
            motor.backward();
        else if (0 > power)
            motor.forward();
        else
            motor.flt(true);
    }

    @Override
    public void pause(int timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            LCD.drawString(e.getMessage(), 0, 0);
        }
    }
}
