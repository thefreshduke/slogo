package commands;

import commandParser.CommandFactory;

public class RepeatCommand extends ControlCommand {

	private BaseCommand myExpression;
	private BaseCommand myInternalCommand;
	
	public RepeatCommand(String userInput, boolean isExpression) {
		super(userInput, isExpression);
	}

	@Override
	public double execute() {
		double returnValue = 0;
		double expressionResult = executeCommand(myExpression);
		if(expressionResult < 1){
			returnValue = 0;
		}
		else{
			int repetitionCount = (int)expressionResult;
			for(int i = 0; i < repetitionCount; i++){
				returnValue = executeCommand(myInternalCommand);
			}
		}
		if(getNextCommand() != null){
			returnValue = executeCommand(getNextCommand());
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