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
        double movedDistance = getExpressionList()[0].execute();
        turtle.moveTowardsHeading(movedDistance);
        grid.update(turtle.getActiveTurtles());
        return movedDistance;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }
}
