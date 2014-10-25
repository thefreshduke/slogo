package commands.turtleCommands;

import backendExceptions.BackendException;

import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

public class TowardsCommand extends TurtleCommand {

    public TowardsCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected int getArgumentCount () {
        return 2;
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer grid = getGridContainer();
        BaseTurtleContainer turtle = getTurtleContainer();
        double newXPos = getExpressionList()[0].execute();
        double newYPos = getExpressionList()[1].execute();
        double degreesTurned = turtle.towardsPosition(newXPos, newYPos);
        grid.update(turtle.getActiveTurtles());

        return degreesTurned;
    }
}