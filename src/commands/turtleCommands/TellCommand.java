package commands.turtleCommands;

import backendExceptions.BackendException;

public class TellCommand extends MultipleTurtleCommand {

	public TellCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}
	
	@Override
	protected double onExecute() throws BackendException {
		addFutureActiveTurtleTurtles();
		String strLastActiveID = myTurtleIDs[myTurtleIDs.length-1];
		double lastActiveID = Double.parseDouble(strLastActiveID); 
		return lastActiveID;
	}
	
	@Override
	protected void parseArguments(String userInput) throws BackendException {
		String[] splitInput = splitByInnerListCommand(userInput);
		String innerInput = splitInput[0];
		storeFutureActiveIDs(innerInput);
		String nextCommandInput = splitInput[1];
		setLeftoverCommands(nextCommandInput);
	}
}
