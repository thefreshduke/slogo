package commands.viewCommands;

import backendExceptions.BackendException;

import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

public class HomeCommand extends TurtleCommand {

    private static final String CLEAR_GUI = "clearGUI";

    public HomeCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(CLEAR_GUI);
        BaseTurtleContainer turtle = getTurtleContainer();
        double distanceTraveled = turtle.setPosition(0, 0);
        return distanceTraveled;
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}
