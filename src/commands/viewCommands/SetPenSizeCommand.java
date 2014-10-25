package commands.viewCommands;

import backendExceptions.BackendException;

import commands.ViewCommand;
import commands.information.BaseGridContainer;

public class SetPenSizeCommand extends ViewCommand {

    // TODO : Add GUI function to change to given index
    private static final String SET_PEN_SIZE = "penThickness";

    public SetPenSizeCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        double penSizeIndex = getExpressionList()[0].execute();
        grid.updateDisplayOptions(SET_PEN_SIZE, penSizeIndex);
        return penSizeIndex;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }

}
