package commands.expressionCommands;

import backendExceptions.BackendException;

public class ExponentCommand extends TwoExpressionCommand {

    public ExponentCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double base = executeCommand(getFirstExpression());
        double exponent = executeCommand(getSecondExpression());
        return Math.pow(base, exponent);
    }

}
