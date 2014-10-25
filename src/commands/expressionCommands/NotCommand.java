package commands.expressionCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class NotCommand extends OneExpressionCommand {

    public NotCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double result = (executeCommand(getArgument()) == 0) ? 1 : 0;
        return result;
    }
}