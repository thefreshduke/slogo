package commands.expressionCommands;

import backendExceptions.BackendException;

public class QuotientCommand extends TwoExpressionCommand {

    public QuotientCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double firstExpression = executeCommand(getFirstExpression());
        double secondExpression = executeCommand(getSecondExpression());
        double result = firstExpression / secondExpression;
        return result;
    }
}
