package commands.expressionCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class ExponentCommand extends TwoExpressionCommand {

    public ExponentCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double onExecute () throws BackendException {
        double base = getFirstExpression().execute();
        double exponent = getSecondExpression().execute();
        return Math.pow(base, exponent);
    }
}