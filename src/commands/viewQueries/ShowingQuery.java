package commands.viewQueries;

import backendExceptions.BackendException;

import commands.ViewQuery;
import commands.information.BaseGridContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class ShowingQuery extends ViewQuery {

    // TODO add the string of function to be called.
    private static final String GET_SHAPE_INDEX = "";

    public ShowingQuery (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(GET_SHAPE_INDEX);
        // TODO get correct return value
        return 0;
    }
}
