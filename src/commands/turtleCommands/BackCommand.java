package commands.turtleCommands;

import backendExceptions.BackendException;

import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;

public class BackCommand extends TurtleCommand {

    public BackCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseGridContainer gridContainer = getGridContainer();
        BaseTurtleContainer turtle = getTurtleContainer();
        double movedDistance = getExpressionList()[0].execute();
        turtle.moveTowardsHeading(movedDistance * -1);
        gridContainer.update(turtle.getActiveTurtles());
        return movedDistance;
    }

    @Override
    protected int getArgumentCount () {
        return 1;
    }
}
