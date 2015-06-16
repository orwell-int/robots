package orwell.tank.states;

import lejos.mf.common.UnitMessageType;

/**
 * Created by Michaël Ludmann on 6/16/15.
 */
public interface RobotState {
    String getValue();

    UnitMessageType getUnitMessageType();
}
