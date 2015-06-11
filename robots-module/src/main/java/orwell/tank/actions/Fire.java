package orwell.tank.actions;

import orwell.tank.IActionVisitor;
import orwell.tank.Tank;
import orwell.tank.elements.RfidFlagSensor;
import orwell.tank.elements.SoundSpeaker;
import orwell.tank.elements.DrivingTracksRegulated;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/11/15.
 */
public class Fire implements IActionVisitor {
    private final boolean hasFire;
    private boolean leftWeapon;
    private boolean rightWeapon;

    public Fire(List<String> fireInput) {
        if (2 != fireInput.size()) {
            hasFire = false;
        } else {
            leftWeapon = Boolean.getBoolean(fireInput.get(0));
            rightWeapon = Boolean.getBoolean(fireInput.get(1));
            hasFire = true;
        }
    }

    public boolean hasFire() {
        return hasFire;
    }

    public boolean hasLeftWeaponFired() {
        return leftWeapon;
    }

    public boolean hasRightWeaponFired() {
        return rightWeapon;
    }

    @Override
    public void visit(Tank tank) {

    }

    @Override
    public void visit(DrivingTracksRegulated tracks) {
        tracks.simulateRecoil();
    }

    @Override
    public void visit(RfidFlagSensor rfidFlagSensor) {

    }

    @Override
    public void visit(SoundSpeaker speaker) {
        speaker.playActionTone(this);
    }
}
