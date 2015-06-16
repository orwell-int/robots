package orwell.tank;

import lejos.nxt.I2CPort;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.DrivingTracksRegulated;
import orwell.tank.elements.SoundSpeaker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Tank {
    private static final lejos.nxt.TachoMotorPort MOTOR_PORT_LEFT = MotorPort.B;
    private static final lejos.nxt.TachoMotorPort MOTOR_PORT_RIGHT = MotorPort.C;
    private static final I2CPort RFID_PORT = SensorPort.S2;
    private static final int THREAD_POOL_NUMBER = 2;
    private String bluetoothName;
    private String routingId;
    private final DrivingTracksRegulated drivingTracks;
    private final RfidFlagSensor rfidFlagSensor;
    private final SoundSpeaker soundSpeaker;
    private EnumConnectionState connectionState;

    public Tank() {
        this.drivingTracks = new DrivingTracksRegulated(
                new NXTRegulatedMotor(MOTOR_PORT_LEFT),
                new NXTRegulatedMotor(MOTOR_PORT_RIGHT));
        this.rfidFlagSensor = new RfidFlagSensor(RFID_PORT);
        this.soundSpeaker = new SoundSpeaker();
        setConnectionState(EnumConnectionState.NOT_CONNECTED);
    }

    public void accept(final ITankVisitor visitor) {
        visitor.visit(drivingTracks);
        visitor.visit(rfidFlagSensor);
        visitor.visit(this);
    }

    public void stop() {

    }

    public RfidFlagSensor getRfidFlagSensor() {
        return rfidFlagSensor;
    }

    public EnumConnectionState getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(EnumConnectionState connectionState) {
        this.connectionState = connectionState;
    }
}
