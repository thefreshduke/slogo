package commands.controlCommands;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import communicator.IVariableContainer;

public class ForCommand extends ControlCommand {
	private String myVariableName;
	private BaseCommand myStartCommand;
	private BaseCommand myEndCommand;
	private BaseCommand myIncrementCommand;
	private BaseCommand myInternalCommand;

	public ForCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double execute(IVariableContainer variableContainer) throws BackendException {
		double returnValue = 0;
		int startValue = (int) executeCommand(myStartCommand, variableContainer);
		int stopValue = (int) executeCommand(myEndCommand, variableContainer);
		int incrementValue = (int) executeCommand(myIncrementCommand, variableContainer);


		for(int i = startValue; i < stopValue; i+=incrementValue){
			variableContainer.addVariable(myVariableName, i);
			returnValue = executeCommand(myInternalCommand, variableContainer);
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

		myStartCommand = CommandFactory.createCommand(innerArguments[1].trim(), true);
		myEndCommand = CommandFactory.createCommand(myStartCommand.getLeftoverString(), true);
		myIncrementCommand = CommandFactory.createCommand(myEndCommand.getLeftoverString(), true);
		

		// Now matching the second set of braces to get internal commands 
		int closingSecondBracketIndex = findClosingBracketIndex(userInput.substring(closingBracketIndex+1));
		myInternalCommand = CommandFactory.createCommand(userInput.substring(closingBracketIndex+1, closingSecondBracketIndex), false); 

		setLeftoverCommands(userInput.substring(closingSecondBracketIndex +1).trim());
	}
}