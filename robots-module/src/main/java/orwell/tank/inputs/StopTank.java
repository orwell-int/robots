package orwell.tank.inputs;

import orwell.tank.IInputVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class StopTank implements IInputVisitor {
    public StopTank(List<String> payloadBody) {

    }

    @Override
    public void visit(Tank tank) {
    }

    @Override
    public void visit(IDrivingTracks tracks) {
        tracks.stop();
    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {
        rfidFlagSensor.stopListen();
    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playStopTank();
    }

    @Override
    public void visit(DisplayScreen screen) {
        screen.printStopTank();
    }
}
