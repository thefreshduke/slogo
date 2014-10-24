package commands.expressionCommands;

import backendExceptions.BackendException;

public class QuotientCommand extends DoubleExpressionCommand {

    public QuotientCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double expressionExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) / executeCommand(getSecondExpression());
        return returnValue;
    }
}