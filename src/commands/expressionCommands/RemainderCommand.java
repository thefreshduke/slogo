package commands.expressionCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class RemainderCommand extends TwoExpressionCommand {

    public RemainderCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double onExecute () throws BackendException {
        double firstExpression = getFirstExpression().execute();
        double secondExpression = getSecondExpression().execute();
        double result = firstExpression % secondExpression;
        return result;
    }
}