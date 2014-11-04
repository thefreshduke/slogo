package commands.logicCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class ArcTanCommand extends OneExpressionLogicCommand {

    public ArcTanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double onExecute () throws BackendException {
        double angle = getArgument().execute();
        return Math.atan(Math.toRadians(angle));
    }
}