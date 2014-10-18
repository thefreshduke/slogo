package commands.mathCommands;

import backendExceptions.BackendException;

public class TanCommand extends SingleExpressionMathCommand{

    public TanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double mathExecute() throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.tan(Math.toRadians(angle));
    }
}