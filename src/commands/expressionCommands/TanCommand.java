package commands.expressionCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class TanCommand extends OneExpressionCommand {

    public TanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double onExecute () throws BackendException {
        double angle = getArgument().execute();
        return Math.tan(Math.toRadians(angle));
    }
}