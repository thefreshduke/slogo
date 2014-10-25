package commands.expressionCommands;

import backendExceptions.BackendException;

public class OrCommand extends TwoExpressionCommand {

    public OrCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double firstExpression = executeCommand(getFirstExpression());
        double secondExpression = executeCommand(getSecondExpression());
        double result = (firstExpression != 0 || secondExpression != 0) ? 1 : 0;
        return result;
    }

}
