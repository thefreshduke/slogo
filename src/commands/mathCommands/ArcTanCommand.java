package commands.mathCommands;

import backendExceptions.BackendException;

public class ArcTanCommand extends SingleExpressionMathCommand{

    public ArcTanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double mathExecute () throws BackendException {
        double angle = executeCommand(getArgument());
        return Math.atan(Math.toRadians(angle));
    }
}
