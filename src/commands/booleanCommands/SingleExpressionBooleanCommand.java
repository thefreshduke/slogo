package commands.booleanCommands;

import commands.BaseCommand;
import backendExceptions.BackendException;

public abstract class SingleExpressionBooleanCommand extends BooleanCommand {

    public SingleExpressionBooleanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    protected BaseCommand getArgument() {
        return getExpressionList()[0];
    }
    
    @Override
    protected final int getArgumentCount() {
        return 1;
    }
}
