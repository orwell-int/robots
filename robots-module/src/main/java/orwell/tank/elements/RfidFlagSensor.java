package orwell.tank.elements;

import lejos.mf.common.UnitMessageType;
import lejos.nxt.I2CPort;
import lejos.nxt.addon.RFIDSensor;
import lejos.util.Timer;
import lejos.util.TimerListener;
import orwell.tank.ISensorListener;

import java.util.ArrayList;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class RfidFlagSensor extends RFIDSensor {

    private static final int READ_RATE_MS = 50;
    private volatile long rfidValue;
    private Timer timer;
    private ArrayList<ISensorListener> sensorListenerList;

    /**
     * Create a class to provide access to the device. Perform device
     * initialization.
     *
     * @param port The sensor port to use for this device.
     */
    public RfidFlagSensor(I2CPort port) {
        super(port);
        sensorListenerList = new ArrayList<>();
        // Schedule a sensor reading task at a fixed period
        timer = new Timer(READ_RATE_MS, new SensorReadService());
    }

    public void addSensorListener(ISensorListener sensorListener) {
        sensorListenerList.add(sensorListener);
    }

    public void startReading() {
        timer.start();
    }

    public void stopReading() {
        timer.stop();
    }

    public long getRfidValue() {
        return rfidValue;
    }

    public void setRfidValue(long rfidValue) {
        if (this.rfidValue == rfidValue)
            return;
        this.rfidValue = rfidValue;
        for (ISensorListener listener : sensorListenerList) {
            listener.readNewValue(UnitMessageType.Rfid, Long.toString(rfidValue));
        }
    }

    private class SensorReadService implements TimerListener {

        @Override
        public void timedOut() {
            setRfidValue(readTransponderAsLong(true));
        }
    }
}
