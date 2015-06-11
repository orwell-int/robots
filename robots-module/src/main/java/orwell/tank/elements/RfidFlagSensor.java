package orwell.tank.elements;

import lejos.nxt.I2CPort;
import lejos.nxt.addon.RFIDSensor;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class RfidFlagSensor extends RFIDSensor {
    /**
     * Create a class to provide access to the device. Perform device
     * initialization.
     *
     * @param port The sensor port to use for this device.
     */
    public RfidFlagSensor(I2CPort port) {
        super(port);
    }
}
