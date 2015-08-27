package orwell.tank.config;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import orwell.tank.utils.IniFile;

import java.io.File;

/**
 * Created by Michaël Ludmann on 8/27/15.
 */
public class TankFileParser {

    private final IniFile iniFile;
    private final String tankFileNameElement = "tank";
    private final String defaultTankFileName = "config.tank.defaults.ini";
    private static final String LEFT_MOTOR_PORT_FIELD = "leftMotorPort";
    private static final String RIGHT_MOTOR_PORT_FIELD = "rightMotorPort";
    private static final String IS_LEFT_MOTOR_INVERTED_FIELD = "isLeftMotorInverted";
    private static final String IS_RIGHT_MOTOR_INVERTED_FIELD = "isRightMotorInverted";
    private static final String RFID_SENSOR_PORT_FIELD = "rfidSensorPort";

    public TankFileParser() {
        String validatedFileName = getValidatedFileName();
        assert (null != validatedFileName);
        this.iniFile = new IniFile(validatedFileName);
    }

    private String getValidatedFileName() {
        // Try to find custom config
        for (File file : File.listFiles()) {
            if (isCustomFileValid(file)) {
                return file.getName();
            }
        }

        // Return the default config if it exists
        if ((new File(defaultTankFileName)).exists()) {
            return defaultTankFileName;
        }

        return null;
    }

    private boolean isCustomFileValid(File file) {
        return file.exists() &&
                file.getName().equalsIgnoreCase(defaultTankFileName) &&
                file.getName().toLowerCase().indexOf(tankFileNameElement.toLowerCase()) >= 0;
    }

    public TankFileBom parse() throws Exception {
        TankFileBom tankFileBom = new TankFileBom();

        setLeftMotorPortTo(tankFileBom);
        setRightMotorPortTo(tankFileBom);
        setIsLeftMotorInvertedTo(tankFileBom);
        setIsRightMotorInvertedTo(tankFileBom);
        setRfidSensorPortTo(tankFileBom);

        return tankFileBom;
    }

    private void setLeftMotorPortTo(TankFileBom tankFileBom) throws Exception {
        String value = iniFile.getProperty(LEFT_MOTOR_PORT_FIELD);
        switch (value) {
            case "A":
                tankFileBom.setLeftMortPort(MotorPort.A);
                break;
            case "B":
                tankFileBom.setLeftMortPort(MotorPort.B);
                break;
            case "C":
                tankFileBom.setLeftMortPort(MotorPort.C);
                break;
            default:
                throw new Exception("IncorrectIniValue");
        }
    }

    private void setRightMotorPortTo(TankFileBom tankFileBom) throws Exception {
        String value = iniFile.getProperty(RIGHT_MOTOR_PORT_FIELD);
        switch (value) {
            case "A":
                tankFileBom.setRightMotorPort(MotorPort.A);
                break;
            case "B":
                tankFileBom.setRightMotorPort(MotorPort.B);
                break;
            case "C":
                tankFileBom.setRightMotorPort(MotorPort.C);
                break;
            default:
                throw new Exception("IncorrectIniValue");
        }
    }

    public void setIsLeftMotorInvertedTo(TankFileBom tankFileBom) {
        tankFileBom.setIsLeftMotorInverted(iniFile.getBoolean(IS_LEFT_MOTOR_INVERTED_FIELD));
    }

    public void setIsRightMotorInvertedTo(TankFileBom tankFileBom) {
        tankFileBom.setIsRightMotorInverted(iniFile.getBoolean(IS_RIGHT_MOTOR_INVERTED_FIELD));
    }

    private void setRfidSensorPortTo(TankFileBom tankFileBom) throws Exception {
        int value = iniFile.getInt(RFID_SENSOR_PORT_FIELD);
        switch (value) {
            case 1:
                tankFileBom.setRfidSensorPort(SensorPort.S1);
                break;
            case 2:
                tankFileBom.setRfidSensorPort(SensorPort.S2);
                break;
            case 3:
                tankFileBom.setRfidSensorPort(SensorPort.S3);
                break;
            case 4:
                tankFileBom.setRfidSensorPort(SensorPort.S4);
                break;
            default:
                throw new Exception("IncorrectIniValue");
        }
    }
}
