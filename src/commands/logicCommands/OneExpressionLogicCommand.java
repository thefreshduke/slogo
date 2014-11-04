package commands.logicCommands;

import backendExceptions.BackendException;

import commands.BaseCommand;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class OneExpressionLogicCommand extends LogicCommand {

    public OneExpressionLogicCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    protected BaseCommand getArgument () {
        return getExpressionList()[0];
    }

    @Override
    protected final int getExpressionCount () {
        return 1;
    }
}