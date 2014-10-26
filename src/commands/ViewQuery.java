package commands;

import backendExceptions.BackendException;
import commands.turtleCommands.TurtleCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class ViewQuery extends ViewCommand {

    private static final String INVALID_ARGUMENT_COUNT_FOR_VIEW_QUERY = "Invalid argument count for turtle query";

	public ViewQuery (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
	protected void parseArguments (String userInput) throws BackendException {
		int argumentCount = getArgumentCount();
		if (argumentCount!= 0) {
			throw new BackendException(null, INVALID_ARGUMENT_COUNT_FOR_VIEW_QUERY);
		} else {
			setLeftoverCommands(userInput);
		}
	}
    
    @Override
    protected int getArgumentCount () {
        return 0;
    }
}
