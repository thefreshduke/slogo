package commands.logicCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class CosCommand extends OneExpressionLogicCommand {

    public CosCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double onExecute () throws BackendException {
        double angle = getArgument().execute();
        return Math.cos(Math.toRadians(angle));
    }
}