package commands.viewCommands;

import backendExceptions.BackendException;

import commands.ViewCommand;
import commands.information.BaseGridContainer;

public class SetShapeCommand extends ViewCommand {

    // TODO : Add GUI function to change to given index
    private static final String SET_SHAPE = "";

    public SetShapeCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        double shapeIndex = getExpressionList()[0].execute();
        grid.updateDisplayOptions(SET_SHAPE, shapeIndex);
        return shapeIndex;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }
}
