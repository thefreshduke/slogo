package commands.viewQueries;

import backendExceptions.BackendException;

import commands.ViewQuery;
import commands.information.BaseGridContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class ShapeQuery extends ViewQuery {

    // TODO figure out the method to call on the frontend to get the result of
    // this operation.
    private static final String GET_SHAPE_INDEX = "";

    public ShapeQuery (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        // grid.updateDisplayOptions(GET_SHAPE_INDEX); Not implemented as front end GUI function yet
        // TODO Change default return value
        return 0;
    }
}
