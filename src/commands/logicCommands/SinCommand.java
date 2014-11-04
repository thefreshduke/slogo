package commands.logicCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class SinCommand extends OneExpressionLogicCommand {

    public SinCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double onExecute () throws BackendException {
        double angle = getArgument().execute();
        return Math.sin(Math.toRadians(angle));
    }
}