package commands.expressionCommands;

import backendExceptions.BackendException;

public class DifferenceCommand extends DoubleExpressionCommand {

    public DifferenceCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) - executeCommand(getSecondExpression());
        return returnValue;
    }

}
