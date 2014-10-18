package commands;

import java.util.Stack;
import commandParser.CommandFactory;
import backendExceptions.BackendException;

public class IfElseCommand extends ControlCommand {

    private BaseCommand myIfCommand;
    private BaseCommand myElseCommand;
    private BaseCommand myExpression;
    
    public IfElseCommand (String userInput, boolean isExpression) {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute() throws BackendException {
        double returnValue;
        if(executeCommand(myExpression) != 0){
            returnValue = executeCommand(myIfCommand);
        }
        else{
            returnValue = executeCommand(myElseCommand);
        }
        if(getNextCommand() != null){
            returnValue = executeCommand(getNextCommand());
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
        myIfCommand = CommandFactory.createCommand(ifCommandInput, true);
        
        int elseCommandStartIndex = firstClosingBracketIndex + 1;
        String elseCommandInputUntrimmed = innerCommandsInput.substring(elseCommandStartIndex).trim();
        int secondClosingBracketIndex = findClosingBracketIndex(elseCommandInputUntrimmed);
        String elseCommandInput = elseCommandInputUntrimmed.substring(1, secondClosingBracketIndex).trim();
        myElseCommand = CommandFactory.createCommand(elseCommandInput, true);
        
        
        String leftOverString = innerCommandsInput.substring(elseCommandStartIndex + secondClosingBracketIndex + 2).trim();
        setLeftoverCommands(leftOverString);
    }
}
