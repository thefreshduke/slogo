package commands.mathCommands;

import commands.BaseCommand;
import backendExceptions.BackendException;

public abstract class SingleExpressionMathCommand extends MathCommand{

    public SingleExpressionMathCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    protected BaseCommand getArgument(){
        return getExpressionList()[0];
    }
    
    @Override
    protected final int getArgumentCount(){
        return 1;
    }
}
