package commands.expressionCommands;

import backendExceptions.BackendException;

public class CosCommand extends OneExpressionCommand {

    public CosCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.cos(Math.toRadians(angle));
    }

}
