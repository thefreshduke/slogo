package commands.expressionCommands;

import commands.BaseCommand;
import backendExceptions.BackendException;

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
