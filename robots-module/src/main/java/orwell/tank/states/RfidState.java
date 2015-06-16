package orwell.tank.states;

import lejos.mf.common.UnitMessageType;

/**
 * Created by Michaël Ludmann on 6/16/15.
 */
public class RfidState implements RobotState {
    private final String currentValue;
    private final UnitMessageType unitMessageType;

    public RfidState(String currentValue) {
        this.currentValue = currentValue;
        this.unitMessageType = UnitMessageType.Rfid;
    }

    @Override
    public String getValue() {
        return this.currentValue;
    }

    @Override
    public UnitMessageType getUnitMessageType() {
        return unitMessageType;
    }
}
