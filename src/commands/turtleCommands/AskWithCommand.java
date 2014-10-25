package commands.turtleCommands;

import backendExceptions.BackendException;

public class AskWithCommand extends TurtleCommand{

	private String myCondition;
	private String myInnerCommand;
	
	public AskWithCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected int getArgumentCount() {
		return 0;
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		String[] splitConditionCommand = splitByInnerListCommand(userInput.trim());
		myCondition = splitConditionCommand[0];
		String[] splitCommandLeftover = splitByInnerListCommand(splitConditionCommand[1]);
		myInnerCommand = splitCommandLeftover[0];
		setLeftoverCommands(splitCommandLeftover[1]);
	}
	
	@Override
	protected double onExecute() throws BackendException {
		// TODO Auto-generated method stub
		return 0;
	}

}
