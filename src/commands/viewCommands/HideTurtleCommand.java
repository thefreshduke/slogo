package commands.viewCommands;

import backendExceptions.BackendException;

import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

public class HideTurtleCommand extends TurtleCommand {

    public HideTurtleCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        BaseTurtleContainer turtle = getTurtleContainer();
        return 0;
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}
