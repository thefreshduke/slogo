package commands.logicCommands;

import backendExceptions.BackendException;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class NotCommand extends OneExpressionLogicCommand {

    public NotCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double onExecute () throws BackendException {
        double result = ((getArgument().execute()) == 0) ? 1 : 0;
        return result;
    }
}