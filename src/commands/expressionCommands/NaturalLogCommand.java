package commands.expressionCommands;

import backendExceptions.BackendException;

public class NaturalLogCommand extends OneExpressionCommand {

    public NaturalLogCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double result = Math.log(executeCommand(getArgument()));
        return result;
    }

}
