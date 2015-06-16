package orwell.tank;

import orwell.tank.elements.DisplayScreen;
import orwell.tank.elements.IDrivingTracks;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;
import orwell.tank.states.RfidState;

/**
 * Created by Michaël Ludmann on 6/16/15.
 */
public class StateSenderVisitor implements ITankVisitor {

    private final UnitMessageBroker unitMessageBroker;

    public StateSenderVisitor(UnitMessageBroker unitMessageBroker) {
        this.unitMessageBroker = unitMessageBroker;
    }

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(IDrivingTracks tracks) {

    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {
        unitMessageBroker.sendState(new RfidState(rfidFlagSensor.getRfidValue()));
    }

    @Override
    public void visit(SoundSpeaker speaker) {

    }

    @Override
    public void visit(DisplayScreen screen) {

    }
}
