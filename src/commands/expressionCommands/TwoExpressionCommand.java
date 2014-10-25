package commands.expressionCommands;

import backendExceptions.BackendException;

import commands.BaseCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class TwoExpressionCommand extends ExpressionCommand {

    public TwoExpressionCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected final int getArgumentCount () {
        return 2;
    }

    protected BaseCommand getFirstExpression () {
        return getExpressionList()[0];
    }

    protected BaseCommand getSecondExpression () {
        return getExpressionList()[1];
    }
}