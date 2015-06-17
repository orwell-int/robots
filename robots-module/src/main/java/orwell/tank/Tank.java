package orwell.tank;

import lejos.nxt.I2CPort;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import orwell.tank.elements.*;

import java.util.ArrayList;


/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Tank {
    private static final lejos.nxt.TachoMotorPort MOTOR_PORT_LEFT = MotorPort.B;
    private static final lejos.nxt.TachoMotorPort MOTOR_PORT_RIGHT = MotorPort.C;
    private static final I2CPort RFID_PORT = SensorPort.S2;
    private final DisplayScreen displayScreen;
    private final DrivingTracksRegulated drivingTracks;
    private final RfidFlagSensor rfidFlagSensor;
    private final SoundSpeaker soundSpeaker;
    private String bluetoothName;
    private String routingId;
    private ArrayList<ISensor> sensors;
    private EnumConnectionState connectionState;

    public Tank() {
        this.drivingTracks = new DrivingTracksRegulated(
                new NXTRegulatedMotor(MOTOR_PORT_LEFT),
                new NXTRegulatedMotor(MOTOR_PORT_RIGHT));
        this.rfidFlagSensor = new RfidFlagSensor(RFID_PORT);
        sensors.add(rfidFlagSensor);
        this.soundSpeaker = new SoundSpeaker();
        this.displayScreen = new DisplayScreen();
        setConnectionState(EnumConnectionState.NOT_CONNECTED);
    }

    public void accept(final ITankVisitor visitor) {
        visitor.visit(drivingTracks);
        visitor.visit(rfidFlagSensor);
        visitor.visit(soundSpeaker);
        visitor.visit(displayScreen);
        visitor.visit(this);
    }

    public void stop() {
        setConnectionState(EnumConnectionState.NOT_CONNECTED);
    }

    public ArrayList<ISensor> getSensors() {
        return sensors;
    }

    public EnumConnectionState getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(EnumConnectionState connectionState) {
        this.connectionState = connectionState;
    }
}
