package commands.mathCommands;

import backendExceptions.BackendException;

public class DifferenceCommand extends ArithmeticCommand {

    public DifferenceCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double mathExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) - executeCommand(getSecondExpression());
        return returnValue;
    }

}
