package commands.controlCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import communicator.IVariableContainer;

public class RepeatCommand extends ControlCommand {

	private BaseCommand myExpression;
	private BaseCommand myInternalCommand;
	
	public RepeatCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double execute(IVariableContainer variableContainer) throws BackendException {
		double returnValue = 0;
		double expressionResult = executeCommand(myExpression, variableContainer);
		if(expressionResult < 1){
			returnValue = 0;
		}
		else{
			int repetitionCount = (int)expressionResult;
			for(int i = 0; i < repetitionCount; i++){
				returnValue = executeCommand(myInternalCommand, variableContainer);
			}
		}
		if(getNextCommand() != null){
			returnValue = executeCommand(getNextCommand(), variableContainer);
		}
		return returnValue;
	}
	
	@Override
	protected void parseArguments(String userInput) {
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