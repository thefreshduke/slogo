package commands.viewQueries;

import backendExceptions.BackendException;

import commands.ViewQuery;
import commands.information.BaseGridContainer;

public class PenDownQuery extends ViewQuery {

    private static final String IS_PEN_DOWN = "";

    public PenDownQuery (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(IS_PEN_DOWN);
        // TODO get value from gridContainer and output
        return 0;
    }
}