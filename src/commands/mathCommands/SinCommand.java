package commands.mathCommands;

import backendExceptions.BackendException;

public class SinCommand extends SingleExpressionCommand{

    public SinCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double expressionExecute() throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.sin(Math.toRadians(angle));
    }
}
