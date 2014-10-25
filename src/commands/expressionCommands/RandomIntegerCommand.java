package commands.expressionCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class RandomIntegerCommand extends OneExpressionCommand {

    public RandomIntegerCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double expressionExecute () throws BackendException {
        double upperLimit = executeCommand(getArgument());
        double randomOrigin = Math.random();
        return (int) randomOrigin * upperLimit;
    }
}