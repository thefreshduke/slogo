package commands.expressionCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class EqualCommand extends TwoExpressionCommand {

    public EqualCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double onExecute () throws BackendException {
        double firstExpression = getFirstExpression().execute();
        double secondExpression = getSecondExpression().execute();
        double result = (firstExpression == secondExpression) ? 1 : 0;
        return result;
    }
}