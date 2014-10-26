package commands.viewQueries;

import backendExceptions.BackendException;

import commands.TurtleQuery;
import commands.information.BaseTurtleContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class HeadingQuery extends TurtleQuery {

    public HeadingQuery (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        double currentHeading = turtle.getOrientation();
        return currentHeading;
    }
}
