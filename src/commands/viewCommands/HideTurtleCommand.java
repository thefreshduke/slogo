package commands.viewCommands;

import backendExceptions.BackendException;

import commands.ViewCommand;
import commands.information.BaseGridContainer;

public class HideTurtleCommand extends ViewCommand {

    // TODO string to add for hide turtle.
    private static final String HIDE_TURTLE = "";

    public HideTurtleCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(HIDE_TURTLE);
        return 0;
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}
