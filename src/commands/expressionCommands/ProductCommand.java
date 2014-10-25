package commands.expressionCommands;

import backendExceptions.BackendException;

public class ProductCommand extends TwoExpressionCommand {

    public ProductCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double firstExpression = executeCommand(getFirstExpression());
        double secondExpression = executeCommand(getSecondExpression());
        double result = firstExpression * secondExpression;
        return result;
    }
}
