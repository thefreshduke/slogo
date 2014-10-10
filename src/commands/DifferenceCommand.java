package commands;

import backendExceptions.BackendException;

public class DifferenceCommand extends ArithmeticCommand {

    public DifferenceCommand (String userInput, boolean isExpression) {
        super(userInput, isExpression);
    }

    @Override
    public double execute () throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) - executeCommand(getSecondExpression());
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand());
        }
        return returnValue;
    }

}
