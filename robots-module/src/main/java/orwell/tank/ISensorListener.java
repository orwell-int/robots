package orwell.tank;

import lejos.mf.common.UnitMessageType;

/**
 * Created by Michaël Ludmann on 6/16/15.
 */
public interface ISensorListener {
    void readNewValue(UnitMessageType unitMessageType, String value); // Will be called when there is a new value ready
}
