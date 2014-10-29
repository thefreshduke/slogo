package commands.viewCommands;

import backendExceptions.BackendException;

import commands.ViewCommand;
import commands.information.BaseGridContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class PenDownCommand extends ViewCommand {

    private static final String PEN_DOWN = "penDown";

    public PenDownCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(PEN_DOWN);
        return 1;
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}
