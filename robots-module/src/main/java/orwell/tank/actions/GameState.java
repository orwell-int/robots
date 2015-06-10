package orwell.tank.actions;

import orwell.tank.IActionVisitor;
import orwell.tank.Tank;

import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class GameState implements IActionVisitor {
    public GameState(List<String> payloadBody) {

    }

    @Override
    public void visit(Tank tank) {

    }
}
