package commands.expressionCommands;

import java.util.Random;
import backendExceptions.BackendException;

public class RandomIntegerCommand extends SingleExpressionCommand{

    public RandomIntegerCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute() throws BackendException {
        double limit = executeCommand(getArgument());
        double randomOrigin = Math.random();
        return (int)randomOrigin*limit;
    }

}
