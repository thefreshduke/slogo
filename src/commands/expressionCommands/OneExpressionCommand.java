package commands.expressionCommands;

import backendExceptions.BackendException;

import commands.BaseCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class OneExpressionCommand extends ExpressionCommand {

    public OneExpressionCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    protected BaseCommand getArgument () {
        return getExpressionList()[0];
    }

    @Override
    protected final int getArgumentCount () {
        return 1;
    }
}