package commands.mathCommands;

import backendExceptions.BackendException;

public class NegativeCommand extends SingleExpressionMathCommand{

    public NegativeCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double mathExecute () throws BackendException {
        return -executeCommand(getArgument());
    }

    
}
