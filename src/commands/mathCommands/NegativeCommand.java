package commands.mathCommands;

import backendExceptions.BackendException;

public class NegativeCommand extends SingleExpressionCommand{

    public NegativeCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double expressionExecute () throws BackendException {
        return -executeCommand(getArgument());
    }

    
}
