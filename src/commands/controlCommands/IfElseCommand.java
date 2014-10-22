package commands.controlCommands;

import java.util.Stack;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.IVariableContainer;
import backendExceptions.BackendException;

public class IfElseCommand extends ControlCommand {

    private BaseCommand myIfCommand;
    private BaseCommand myElseCommand;
    private BaseCommand myExpression;
    
    public IfElseCommand (String userInput, boolean isExpression) throws BackendException{
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute(IVariableContainer variableContainer) throws BackendException {
        double returnValue;
        if(executeCommand(myExpression, variableContainer) != 0){
            returnValue = executeCommand(myIfCommand, variableContainer);
        }
        else{
            returnValue = executeCommand(myElseCommand, variableContainer);
        }
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand(), variableContainer);
        }
        return returnValue;
    }

    @Override
    protected void parseArguments (String userInput) {
        myExpression = CommandFactory.createCommand(userInput, true);
        String innerCommandsInput = myExpression.getLeftoverString().trim();
        
        String[] firstSplitCommand = splitByInnerListCommand(innerCommandsInput);
        String ifCommandInput = firstSplitCommand[0];
        
        myIfCommand = CommandFactory.createCommand(ifCommandInput, false);
        
        String[] secondSplitCommand = splitByInnerListCommand(firstSplitCommand[1]);
        String elseCommandInput = secondSplitCommand[0];
        myElseCommand = CommandFactory.createCommand(elseCommandInput, false);
        
        String leftOverString = secondSplitCommand[1];
        setLeftoverCommands(leftOverString);
    }
}
