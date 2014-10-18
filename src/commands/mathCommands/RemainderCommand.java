package commands.mathCommands;

import backendExceptions.BackendException;

public class RemainderCommand extends ArithmeticCommand{

    public RemainderCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double mathExecute () throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) % executeCommand(getSecondExpression());
        return returnValue;
    }
}
