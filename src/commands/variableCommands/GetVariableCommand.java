package commands.variableCommands;

import commands.BaseCommand;
import commands.information.BaseVariableContainer;
import backendExceptions.BackendException;

public class GetVariableCommand extends VariableCommand {

	private String myVariable;
	
	public GetVariableCommand(String userInput, boolean isExpression)
			throws BackendException {
		super(userInput, isExpression);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseVariableContainer variableContainer = getVariableContainer();
		BaseCommand command = variableContainer.getValue(myVariable);
		return command.execute();
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
