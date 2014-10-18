package commands.variableCommands;

import commands.BaseCommand;
import communicator.IVariableContainer;
import backendExceptions.BackendException;

public class GetVariableCommand extends VariableCommand {

	private String myVariable;
	
	public GetVariableCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double execute(IVariableContainer variableContainer)
			throws BackendException {
		BaseCommand command = variableContainer.getValue(myVariable);
		return executeCommand(command, variableContainer);
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		String[] inputList = userInput.split(COMMAND_DELIMITER);
		if(inputList.length < 1) {
			throw new BackendException(null, "Invalid syntax for retrieving variable");
		}
		myVariable = inputList[0];
		setLeftoverCommands(userInput.replaceFirst(myVariable, "").trim());
	}
}
