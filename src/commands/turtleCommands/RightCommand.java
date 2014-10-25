package commands.turtleCommands;

import backendExceptions.BackendException;

import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

public class RightCommand extends TurtleCommand {

    public RightCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        BaseGridContainer grid = getGridContainer();
        double rotateRight = getExpressionList()[0].execute();
        turtle.rotate(rotateRight);
        grid.update(turtle.getActiveTurtles());
        ;
        return rotateRight;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }
}