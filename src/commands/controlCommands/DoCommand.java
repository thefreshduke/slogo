package commands.controlCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import communicator.IVariableContainer;

public class DoCommand extends ControlCommand {
	private String myVariableName;
	private BaseCommand myLimitCommand;
	private BaseCommand myInternalCommand;
	
	public DoCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double execute(IVariableContainer variableContainer) throws BackendException {
		double returnValue = 0;
		double expressionResult = executeCommand(myLimitCommand, variableContainer);
		
		if(expressionResult < 1){
			returnValue = 0;
		}
		else{
			int repetitionCount = (int)expressionResult;
			
			for(int i = 1; i <= repetitionCount; i++){
				variableContainer.addVariable(myVariableName, repetitionCount);
				returnValue = executeCommand(myInternalCommand, variableContainer);
			}
		}
		if(getNextCommand() != null){
			returnValue = executeCommand(getNextCommand(), variableContainer);
		}
		return returnValue;
	}

	@Override
	protected void parseArguments (String userInput) {
        if(userInput.charAt(0) != COMMAND_INDICATOR){
            //throw 
        }
        int closingBracketIndex = findClosingBracketIndex(userInput);
        String innerInput = userInput.substring(1 , closingBracketIndex).trim();
        
        String [] innerArguments =  innerInput.split(COMMAND_SEPARATOR, 2);
        if (innerArguments.length < 2) {
        	 //TODO throw exception
        }
        myVariableName = innerArguments[0];
        
        myLimitCommand = CommandFactory.createCommand(innerArguments[1].trim(), true);
        
        // Now matching the second set of braces to get command 
        int closingSecondBracketIndex = findClosingBracketIndex(userInput.substring(closingBracketIndex+1));
        myInternalCommand = CommandFactory.createCommand(userInput.substring(closingBracketIndex+1, closingSecondBracketIndex), false); 

        setLeftoverCommands(userInput.substring(closingSecondBracketIndex +1).trim());
    }
}
