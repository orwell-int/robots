package orwell.tank.actions;

import orwell.tank.IActionVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;
import orwell.tank.elements.DrivingTracksRegulated;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class StopProgram implements IActionVisitor {
    public StopProgram(List<String> strings) {

    }

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(DrivingTracksRegulated wheel) {

    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {

    }

    @Override
    public void visit(SoundSpeaker speaker) {

    }
}
