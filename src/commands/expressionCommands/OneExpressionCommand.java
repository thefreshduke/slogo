package commands.expressionCommands;

import commands.BaseCommand;
import backendExceptions.BackendException;

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
