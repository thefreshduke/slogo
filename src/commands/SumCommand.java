package commands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;


public class SumCommand extends ArithmeticCommand{
    
    public SumCommand (String userInput, boolean isExpression) {
        super(userInput, isExpression);
    }

    @Override
    public double execute () throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) + executeCommand(getSecondExpression());
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand());
        }
        return returnValue;
    }
}
