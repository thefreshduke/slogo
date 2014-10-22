package commands.expressionCommands;

import backendExceptions.BackendException;

public class TanCommand extends SingleExpressionCommand{

    public TanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double expressionExecute() throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.tan(Math.toRadians(angle));
    }
}
