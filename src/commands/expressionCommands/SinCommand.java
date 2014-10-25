package commands.expressionCommands;

import backendExceptions.BackendException;

public class SinCommand extends OneExpressionCommand {

    public SinCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.sin(Math.toRadians(angle));
    }

}
