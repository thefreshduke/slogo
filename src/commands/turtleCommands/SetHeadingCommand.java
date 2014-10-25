package commands.turtleCommands;

import backendExceptions.BackendException;

import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

public class SetHeadingCommand extends TurtleCommand {

    public SetHeadingCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        BaseGridContainer grid = getGridContainer();
        double absHeading = (getExpressionList()[0]).execute();
        double rotatedDegrees = turtle.setHeading(absHeading);
        grid.update(turtle.getActiveTurtles());
        return rotatedDegrees;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }
}