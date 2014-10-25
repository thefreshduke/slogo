package commands.expressionCommands;

import backendExceptions.BackendException;

public class ArcTanCommand extends OneExpressionCommand {

    public ArcTanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.atan(Math.toRadians(angle));
    }

}
