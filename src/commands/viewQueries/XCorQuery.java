package commands.viewQueries;

import backendExceptions.BackendException;

import commands.TurtleQuery;
import commands.information.BaseTurtleContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class XCorQuery extends TurtleQuery {

    public XCorQuery (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        double xCoordinate = turtle.getXPos();
        return xCoordinate;
    }
}
