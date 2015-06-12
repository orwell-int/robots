package orwell.tank.actions;

import orwell.tank.IActionVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class StopTank implements IActionVisitor {
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
        rfidFlagSensor.stop();
    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playActionTone(this);
    }
}
