package commands.expressionCommands;

import backendExceptions.BackendException;

public class NegativeCommand extends OneExpressionCommand {

    public NegativeCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double result = -executeCommand(getArgument());
        return result;
    }

}
