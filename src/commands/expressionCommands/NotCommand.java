package commands.expressionCommands;

import backendExceptions.BackendException;

public class NotCommand extends OneExpressionCommand {

    public NotCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double result = (executeCommand(getArgument()) == 0) ? 1 : 0;
        return result;
    }

}
