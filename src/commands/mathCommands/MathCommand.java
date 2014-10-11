package commands.mathCommands;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.LogicCommand;
import backendExceptions.BackendException;


public abstract class MathCommand extends LogicCommand {

    private BaseCommand[] myArgumentList;

    public MathCommand(String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }
    

    @Override
    public final double execute() throws BackendException{
        double result = mathExecute();
        BaseCommand nextCommand = getNextCommand();
        return nextCommand == null ? result : executeCommand(nextCommand);
    }
    
    public abstract double mathExecute() throws BackendException;
    
    @Override
    protected void parseArguments(String userInput) {
        int argumentCount = getArgumentCount();
        if (argumentCount < 0) {
            // TODO: make separate exception
            //throw new BackendException(null, "ff");
        }
        myArgumentList = new BaseCommand[argumentCount];
        for (int i = 0; i < argumentCount; i++) {
            String subInput;
            if (i == 0) {
                subInput = userInput;
            }
            else {
                subInput = myArgumentList[i-1].getLeftoverString();
            }
            if(!subInput.equals("")){
            	BaseCommand argument = CommandFactory.createCommand(subInput, true);
                myArgumentList[i] = argument;
            }
        }
        setLeftoverCommands(myArgumentList[myArgumentList.length-1].getLeftoverString());
    }

    protected BaseCommand[] getExpressionList() {
        return myArgumentList;
    }

    protected abstract int getArgumentCount();
}
