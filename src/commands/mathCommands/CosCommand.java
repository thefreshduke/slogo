package commands.mathCommands;

import backendExceptions.BackendException;

public class CosCommand extends SingleExpressionCommand{

    public CosCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double expressionExecute() throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.cos(Math.toRadians(angle));
    }
}
