package commands.expressionCommands;

import backendExceptions.BackendException;

public class NaturalLogCommand extends SingleExpressionCommand {

    public NaturalLogCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double expressionExecute () throws BackendException {
        double result = executeCommand(getArgument());
        return Math.log(result);
    }

}
