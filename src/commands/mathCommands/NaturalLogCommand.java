package commands.mathCommands;

import backendExceptions.BackendException;

public class NaturalLogCommand extends SingleExpressionMathCommand {

    public NaturalLogCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double mathExecute () throws BackendException {
        double result = executeCommand(getArgument());
        return Math.log(result);
    }

}
