package commands;

import commandParser.CommandFactory;
import backendExceptions.BackendException;

public class IfCommand extends ControlCommand{
    
    private BaseCommand myExpression;
    private BaseCommand myInternalCommand;
    
    public IfCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute() throws BackendException {
        double returnValue = 0;
        double expressionResult = executeCommand(myExpression);
        if(expressionResult != 0){
            executeCommand(myInternalCommand);
        }
        else{
            returnValue = expressionResult;
        }
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand());
        }
        return returnValue;
    }

    @Override
    protected void parseArguments (String userInput) {
        myExpression = CommandFactory.createCommand(userInput, true);
        String leftOver = new String(myExpression.getLeftoverString().trim());
        if(leftOver.charAt(0) != COMMAND_INDICATOR){
            //throw 
        }
        int closingBracketIndex = findClosingBracketIndex(leftOver);
        String innerCommand = leftOver.substring(1 , closingBracketIndex).trim();
        myInternalCommand = CommandFactory.createCommand(innerCommand, true);
        setLeftoverCommands(leftOver.substring(closingBracketIndex +1).trim());
    }
}
