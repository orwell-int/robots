package orwell.tank.actions;

import orwell.tank.IActionVisitor;
import orwell.tank.Tank;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Input implements IActionVisitor {
    public Input(List<String> payloadBody) {

    }

    @Override
    public void visit(Tank tank) {

    }
}
