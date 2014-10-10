package commands;

import java.util.Stack;
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
    public double execute() {
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
        myExpression = TestFactory.createCommand(userInput, true);
        String innerCommandsInput = myExpression.getLeftoverString().trim();
        String[] splitInput = innerCommandsInput.split("\\s+");
        if(splitInput.length <= 0 || !splitInput[0].equals("[")){
            //throw new BackendException(null, "ff");
        }
        
        int firstClosingBracketIndex = findClosingBracketIndex(innerCommandsInput);
        String ifCommandInput = innerCommandsInput.substring(1, firstClosingBracketIndex);
        myIfCommand = TestFactory.createCommand(ifCommandInput, true);
        
        String elseCommandInputUntrimmed = innerCommandsInput.substring(firstClosingBracketIndex + 1).trim();
        
        int secondClosingBracketIndex = findClosingBracketIndex(elseCommandInputUntrimmed);
        String elseCommandInput = elseCommandInputUntrimmed.substring(1, secondClosingBracketIndex);
        myElseCommand = TestFactory.createCommand(elseCommandInput, true);
        
        String leftOverString = innerCommandsInput.substring(secondClosingBracketIndex + 1).trim();
        setLeftoverCommands(leftOverString);
    }
    
    private int findClosingBracketIndex(String input){
        Stack<Character> checkStack = new Stack<>();
        for(int i=0; i < input.length(); i++){
            char character = input.charAt(i);
            if(character == '['){
                checkStack.push(character);
            }
            else if(character == ']'){
                checkStack.pop();
            }
            if(checkStack.size() == 0){
                return i;
            }
        }
        return -1;
    }
}
