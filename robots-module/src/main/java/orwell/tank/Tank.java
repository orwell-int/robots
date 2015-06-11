package orwell.tank;

import lejos.nxt.I2CPort;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.DrivingTracksRegulated;
import orwell.tank.elements.SoundSpeaker;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Tank {
    private static final lejos.nxt.TachoMotorPort MOTOR_PORT_LEFT = MotorPort.B;
    private static final lejos.nxt.TachoMotorPort MOTOR_PORT_RIGHT = MotorPort.C;
    private static final I2CPort RFID_PORT = SensorPort.S2;
    private String bluetoothName;
    private String routingId;
    private final DrivingTracksRegulated drivingTracks;
    private final RfidFlagSensor rfidFlagSensor;
    private final SoundSpeaker soundSpeaker;

    public Tank() {
        this.drivingTracks = new DrivingTracksRegulated(MOTOR_PORT_LEFT, MOTOR_PORT_RIGHT);
        this.rfidFlagSensor = new RfidFlagSensor(RFID_PORT);
        this.soundSpeaker = new SoundSpeaker();
    }

    public void accept(final ITankVisitor visitor) {
        visitor.visit(drivingTracks);
        visitor.visit(rfidFlagSensor);
        visitor.visit(this);
    }
}
