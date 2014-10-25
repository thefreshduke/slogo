package commands;

import backendExceptions.BackendException;

import commands.turtleCommands.TurtleCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class ViewQuery extends TurtleCommand {

    public ViewQuery (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}
