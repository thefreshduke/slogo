package commands.viewQueries;

import backendExceptions.BackendException;

import commands.ViewQuery;
import commands.information.BaseGridContainer;

public class PenColorQuery extends ViewQuery {

    private static final String PEN_COLOR = "";

    public PenColorQuery (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(PEN_COLOR);
        return 0;
    }
}