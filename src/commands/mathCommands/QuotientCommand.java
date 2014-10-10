package commands.mathCommands;

import backendExceptions.BackendException;

public class QuotientCommand extends ArithmeticCommand{

    public QuotientCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double mathExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) / executeCommand(getSecondExpression());
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand());
        }
        return returnValue;
    }
}
