package commands.mathCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.LogicCommand;

public abstract class ArithmeticCommand extends MathCommand {
    
    public ArithmeticCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }
    
    @Override
    protected final int getArgumentCount(){
        return 2;
    }
    
    protected BaseCommand getFirstExpression(){
        return getExpressionList()[0];
    }
    
    protected BaseCommand getSecondExpression(){
        return getExpressionList()[1];
    }
}
