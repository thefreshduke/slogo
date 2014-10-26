package commands.turtleCommands;

import backendExceptions.BackendException;

import commands.TurtleQuery;
import commands.information.BaseTurtleContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class NumberOfTurtlesCommand extends TurtleQuery {

    public NumberOfTurtlesCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        double numTurtles = turtle.getAllTurtles().size();
        return numTurtles;
    }
}
