package commands.mathCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;


public class SumCommand extends ArithmeticCommand{
    
    public SumCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double mathExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) + executeCommand(getSecondExpression());
        return returnValue;
    }
}
