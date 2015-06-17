package orwell.tank;

import lejos.mf.common.MessageListenerInterface;
import lejos.mf.common.UnitMessage;
import lejos.mf.common.UnitMessageType;
import lejos.mf.nxt.MessageFrameworkNXT;
import lejos.nxt.Button;
import orwell.tank.inputs.ConnectedToProxy;
import orwell.tank.inputs.StartTank;
import orwell.tank.inputs.StopProgram;
import orwell.tank.inputs.WaitForProxy;


/**
 * Thread to wait for a Bluetooth connection and execute remote commands
 */
class TankControl extends Thread implements MessageListenerInterface {
    private final Tank tank;
    private volatile boolean isRemoteControlAlive;
    private MessageFrameworkNXT messageFramework;
    private UnitMessageBroker unitMessageBroker;

    public TankControl(Tank tank) {
        this.tank = tank;
    }

    public static void main(String[] args) {
        Tank tank = new Tank();
        TankControl tankControl = new TankControl(tank);
        tankControl.startRemoteControl();
    }

    public void run() {
        isRemoteControlAlive = true;

        while (!Button.ESCAPE.isDown() && isRemoteControlAlive) {
            continue;
        }
        stopProgram();
    }

    private void stopProgram() {
        UnitMessage stopMessage = new UnitMessage(UnitMessageType.Stop, "Escape Button pressed");
        messageFramework.SendMessage(stopMessage);
        unitMessageBroker.stopListen();
    }

    public void stopTankControl() {
        isRemoteControlAlive = false;
    }

    private void startRemoteControl() {
        tank.accept(new StartTank()); // Ready the tank
        waitForConnectionToProxy(); // Establish BT connection
        startMessageBroker(); // Start listening to sensors
        start();
    }

    private void waitForConnectionToProxy() {
        tank.accept(new WaitForProxy());
        messageFramework = MessageFrameworkNXT.getInstance();

        tank.accept(new ConnectedToProxy());
    }

    private void startMessageBroker() {
        unitMessageBroker = new UnitMessageBroker(messageFramework, tank);
        unitMessageBroker.startListen();
    }

    @Override
    public void receivedNewMessage(UnitMessage msg) {
        IInputVisitor IInputVisitor = UnitMessageDecoder.parseFrom(msg);
        tank.accept(IInputVisitor);

        if (IInputVisitor instanceof StopProgram)
            stopTankControl();
    }
}
