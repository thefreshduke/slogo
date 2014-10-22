package commands.controlCommands;

import java.util.Stack;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import communicator.IVariableContainer;
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
        String[] splitInput = innerCommandsInput.split("\\s+");
        if(splitInput.length <= 0 || !splitInput[0].equals("[")){
            //throw new BackendException(null, "ff");
        }
        
        int firstClosingBracketIndex = findClosingBracketIndex(innerCommandsInput);
        String ifCommandInput = innerCommandsInput.substring(1, firstClosingBracketIndex).trim();
        myIfCommand = CommandFactory.createCommand(ifCommandInput, false);
        
        int elseCommandStartIndex = firstClosingBracketIndex + 1;
        String elseCommandInputUntrimmed = innerCommandsInput.substring(elseCommandStartIndex).trim();
        int secondClosingBracketIndex = findClosingBracketIndex(elseCommandInputUntrimmed);
        String elseCommandInput = elseCommandInputUntrimmed.substring(1, secondClosingBracketIndex).trim();
        myElseCommand = CommandFactory.createCommand(elseCommandInput, false);
        
        
        String leftOverString = innerCommandsInput.substring(elseCommandStartIndex + secondClosingBracketIndex + 2).trim();
        setLeftoverCommands(leftOverString);
    }
}
