package orwell.tank;

import orwell.tank.elements.FlagSensor;
import orwell.tank.elements.Wheel;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public interface ITankVisitor {
    void visit(Tank tank);

    void visit(Wheel wheel);

    void visit(FlagSensor flagSensor);
}
