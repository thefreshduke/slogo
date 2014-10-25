package commands.viewCommands;

import backendExceptions.BackendException;

import commands.ViewCommand;
import commands.information.BaseGridContainer;

public class SetPenColorCommand extends ViewCommand {

    // TODO : Add GUI function to change to given index
    private static final String SET_PEN_COLOR = "";

    public SetPenColorCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        double penColorIndex = getExpressionList()[0].execute();
        grid.updateDisplayOptions(SET_PEN_COLOR, penColorIndex);
        return penColorIndex;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }

}
