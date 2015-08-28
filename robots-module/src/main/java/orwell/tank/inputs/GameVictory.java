package orwell.tank.inputs;

import orwell.tank.Tank;
import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;

/**
 * Created by Michaël Ludmann on 07/06/15.
 */
public class GameVictory implements IGameState {
    @Override
    public void visit(DisplayScreen screen) {

    }

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(IDrivingTracks tracks) {
        tracks.setPower(50, -50);
        tracks.pause(1000);
        tracks.setPower(-50, 50);
        tracks.pause(1000);
        tracks.stop();
    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {

    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playVictory();
    }
}
