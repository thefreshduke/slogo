package commands.viewCommands;

import backendExceptions.BackendException;

import commands.ViewCommand;
import commands.information.BaseGridContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class PenUpCommand extends ViewCommand {

    private static final String PEN_UP = "penUp";

    public PenUpCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(PEN_UP);
        return 0;
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}