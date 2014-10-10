package commands;

import backendExceptions.BackendException;

public class ProductCommand extends ArithmeticCommand {

    public ProductCommand (String userInput, boolean isExpression) {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute () throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) * executeCommand(getSecondExpression());
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand());
        }
        return returnValue;
    }
}
