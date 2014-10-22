package commands.controlCommands;

import java.util.List;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import communicator.IVariableContainer;

public class ToCommand extends ControlCommand {
	private String myCommandName;
	private BaseCommand myInternalCommand;
	private List<BaseCommand> myVariableCommandList;
	public ToCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double execute(IVariableContainer variableContainer) throws BackendException {
		return 0;
	}

	@Override
	protected void parseArguments (String userInput) {

		String [] innerArguments =  userInput.trim().split(COMMAND_SEPARATOR, 2);
		if (innerArguments.length < 2) {
			//TODO throw exception
		}
		myCommandName = innerArguments[0];

		if (innerArguments[1].trim().charAt(0)!= '[') {
			//TODO throw exception 
		}
		int firstClosingBracketIndex = findClosingBracketIndex(innerArguments[1].trim().substring(1));

		String variables = innerArguments[1].trim().substring(1, firstClosingBracketIndex);
		String [] variablesList = variables.split(COMMAND_SEPARATOR);
		BaseCommand variableCommand;

		for (String variable : variablesList) { 
			variable = variable.trim();
			variableCommand= CommandFactory.createCommand(variable, false);
			myVariableCommandList.add(variableCommand);
		}

		// Now matching the second set of braces to get command 
		int closingSecondBracketIndex = findClosingBracketIndex(innerArguments[1].trim().substring(firstClosingBracketIndex+1));

		myInternalCommand = CommandFactory.createCommand(innerArguments[1].trim().substring(firstClosingBracketIndex+1, closingSecondBracketIndex), true);

		setLeftoverCommands(innerArguments[1].trim().substring(closingSecondBracketIndex+1).trim());
	}
}
