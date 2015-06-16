package orwell.tank;

import lejos.mf.common.UnitMessage;
import lejos.mf.common.UnitMessageType;
import lejos.mf.nxt.MessageFrameworkNXT;

/**
 * Created by Michaël Ludmann on 6/16/15.
 */
public class UnitMessageBroker implements ISensorListener {
    private final MessageFrameworkNXT messageFramework;

    public UnitMessageBroker(MessageFrameworkNXT messageFramework, Tank tank) {
        this.messageFramework = messageFramework;

        // TODO do not violate Demeter's law
        tank.getRfidFlagSensor().addSensorListener(this);
        tank.getRfidFlagSensor().startReading();

    }

    @Override
    public void readNewValue(UnitMessageType unitMessageType, String value) {
        UnitMessage unitMessage = new UnitMessage(unitMessageType, value);
        messageFramework.SendMessage(unitMessage);
    }
}
