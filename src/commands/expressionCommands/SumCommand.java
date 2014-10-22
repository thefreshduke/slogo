package commands.expressionCommands;

import backendExceptions.BackendException;


public class SumCommand extends DoubleExpressionCommand {
    
    public SumCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) + executeCommand(getSecondExpression());
        return returnValue;
    }
}
