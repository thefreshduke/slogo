// This entire file is part of my masterpiece.
// Rahul Harikrishnan
package commands.turtleCommands;

import backendExceptions.BackendException;

import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
/**
 * 
 * @author Rahul
 *
 */
public class ForwardCommand extends TurtleCommand {

    public ForwardCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        BaseGridContainer grid = getGridContainer();
        double forwardDistance = getExpressionList()[0].execute();
        turtle.moveTowardsHeading(forwardDistance);
        grid.update(turtle.getActiveTurtles());
        return forwardDistance;
    }

    @Override
    protected int getExpressionCount () {
        return 1;
    }
}
