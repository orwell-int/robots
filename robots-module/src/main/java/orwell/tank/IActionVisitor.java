package orwell.tank;


import orwell.tank.elements.DisplayScreen;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public interface IActionVisitor extends ITankVisitor {

    void visit(DisplayScreen screen);
}
