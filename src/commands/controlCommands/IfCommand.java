package commands.controlCommands;

import java.util.Collection;
import java.util.Set;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.IInformationContainer;
import communicator.IVariableContainer;
import backendExceptions.BackendException;

public class IfCommand extends ControlCommand{
    
    private BaseCommand myExpression;
    private BaseCommand myInternalCommand;
    
    public IfCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute(IVariableContainer variableContainer) throws BackendException {
        double returnValue = 0;
        double expressionResult = executeCommand(myExpression, variableContainer);
        if(expressionResult != 0){
            executeCommand(myInternalCommand, variableContainer);
        }
        else{
            returnValue = expressionResult;
        }
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand(), variableContainer);
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
        myInternalCommand = CommandFactory.createCommand(innerCommand, false);
        setLeftoverCommands(leftOver.substring(closingBracketIndex +1).trim());
    }
}
