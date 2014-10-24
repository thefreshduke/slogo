package commands.expressionCommands;

import backendExceptions.BackendException;

public class ProductCommand extends DoubleExpressionCommand {

    public ProductCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double expressionExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) * executeCommand(getSecondExpression());
        return returnValue;
    }
}
