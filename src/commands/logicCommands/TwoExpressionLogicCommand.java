package commands.logicCommands;

import backendExceptions.BackendException;

import commands.BaseCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class TwoExpressionLogicCommand extends LogicCommand {

    public TwoExpressionLogicCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected final int getExpressionCount () {
        return 2;
    }

    protected BaseCommand getFirstExpression () {
        return getExpressionList()[0];
    }

    protected BaseCommand getSecondExpression () {
        return getExpressionList()[1];
    }
}