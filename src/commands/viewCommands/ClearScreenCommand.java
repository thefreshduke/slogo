package commands.viewCommands;

import backendExceptions.BackendException;
import commands.TurtleQuery;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class ClearScreenCommand extends TurtleQuery {

    private static final String CLEAR_GUI = "clear";

    public ClearScreenCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        BaseTurtleContainer turtle = getTurtleContainer();
        double distanceTraveled = turtle.setPosition(0, 0);
        grid.updateDisplayOptions(CLEAR_GUI);
        turtle.clear();
        grid.update(turtle.getActiveTurtles());
        return distanceTraveled;
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}