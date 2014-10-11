package commands.booleanCommands;

import commands.BaseCommand;

import backendExceptions.BackendException;

public abstract class DoubleExpressionBooleanCommand extends BooleanCommand {
	
	public DoubleExpressionBooleanCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

	@Override
	protected int getArgumentCount() {
		return 2;
	}
	
	protected BaseCommand getFirstExpression() {
        return getExpressionList()[0];
    }
    
    protected BaseCommand getSecondExpression() {
        return getExpressionList()[1];
    }

}
