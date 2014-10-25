package commands.expressionCommands;

import backendExceptions.BackendException;

public class NotEqualCommand extends TwoExpressionCommand {

    public NotEqualCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double firstExpression = executeCommand(getFirstExpression());
        double secondExpression = executeCommand(getSecondExpression());
        double result = (firstExpression != secondExpression) ? 1 : 0;
        return result;
    }

}
