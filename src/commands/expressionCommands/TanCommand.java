package commands.expressionCommands;

import backendExceptions.BackendException;

public class TanCommand extends OneExpressionCommand {

    public TanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.tan(Math.toRadians(angle));
    }

}
