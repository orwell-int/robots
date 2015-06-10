package orwell.tank;

import lejos.nxt.I2CPort;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.SensorPort;
import orwell.tank.elements.FlagSensor;
import orwell.tank.elements.Wheel;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Tank {
    private static final lejos.nxt.TachoMotorPort MOTOR_PORT_LEFT = MotorPort.B;
    private static final lejos.nxt.TachoMotorPort MOTOR_PORT_RIGHT = MotorPort.C;
    private static final I2CPort RFID_PORT = SensorPort.S2;
    private String bluetoothName;
    private String routingId;
    private final Wheel motorLeft;
    private final Wheel motorRight;
    private final FlagSensor flagSensor;

    public Tank() {
        this.motorLeft = new Wheel(MOTOR_PORT_LEFT);
        this.motorRight = new Wheel(MOTOR_PORT_RIGHT);
        this.flagSensor = new FlagSensor(RFID_PORT);
    }

    public void stopAllMotors() {
        motorLeft.stop();
        motorRight.stop();
    }

    public void accept(final ITankVisitor visitor) {
        visitor.visit(motorLeft);
        visitor.visit(motorRight);
        visitor.visit(flagSensor);
        visitor.visit(this);
    }
}
