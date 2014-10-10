package commands.mathCommands;

import backendExceptions.BackendException;

public class ExponentCommand extends ArithmeticCommand{

    public ExponentCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double mathExecute () throws BackendException {
        double base = executeCommand(getFirstExpression());
        double exponent = executeCommand(getSecondExpression());
        return Math.pow(base, exponent);
    }

}
