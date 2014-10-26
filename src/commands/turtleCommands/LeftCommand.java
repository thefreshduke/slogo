package commands.turtleCommands;

import backendExceptions.BackendException;

import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class LeftCommand extends TurtleCommand {

    public LeftCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        BaseGridContainer grid = getGridContainer();
        double rotateLeft = getExpressionList()[0].execute();
        turtle.rotate(rotateLeft * -1);
        grid.update(turtle.getActiveTurtles());
        ;
        return rotateLeft;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }
}