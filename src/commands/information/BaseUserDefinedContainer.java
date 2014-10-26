package commands.information;

import java.util.List;
import java.util.Map;

import backendExceptions.BackendException;
import commands.BaseCommand;

/**
 * Container for all user defined inputs (i.e. variables and user defined
 * commands). All variable and command containers must extend from this class in
 * order for all the basic commands to work.
 * 
 * @author Duke
 *
 */
public abstract class BaseUserDefinedContainer implements IInformationContainer {

	/**
	 * Adds a variable.
	 * @param variable Variable name
	 * @param command Command that the variable represents. Not the same as user defined commands.
	 * @return Whether variable was successfully added.
	 * @throws BackendException Any exception while adding.
	 */
	public abstract boolean addVariable(String variable, BaseCommand command)
			throws BackendException;

	/**
	 * Adds a variable.
	 * @param variable Variable name
	 * @param value Double value of the variable. 
	 * @return Whether variable was successfully added.
	 * @throws BackendException Any exception while adding.
	 */
	public abstract boolean addVariable(String variable, double value)
			throws BackendException;

	/**
	 * Gets the value of variable
	 * @param variable Variable to get.
	 * @return Value
	 * @throws BackendException Any exception while fetching value
	 */
	public abstract BaseCommand getValue(String variable)
			throws BackendException;

	/**
	 * Removes a variable from the container.
	 * @param variable Variable to remove
	 * @return Value that the variable corresponded to.
	 */
	public abstract BaseCommand popOffVariable(String variable);

	/**
	 * Adds user defined command
	 * @param commandName User defined command name
	 * @param innerCommands String equivalent that the command represents
	 * @param temporaryVariables Parameters associated with the command.
	 */
	public abstract void addNewCommand(String commandName,
			String innerCommands, String[] temporaryVariables);

	/**
	 * Gets the command equivalent of the user defined command.
	 * @param commandName User defined command to fetch
	 * @param input Parameters for the command.
	 * @param isExpression Whether the command should be created as an expression
	 * @return Command equivalent of usef defined command.
	 * @throws BackendException 
	 */
	public abstract BaseCommand getCreatedCommand(String commandName,
			String input, boolean isExpression) throws BackendException;

	/**
	 * Checks whether container has a specific variable.
	 * @param variable Variable to check. 
	 * @return Whether the container contains the variable.
	 * @throws BackendException Any exception while checking.
	 */
	public boolean containsVariable(String variable) throws BackendException{
		return getAllVariablesAndValues().containsKey(variable);
	}

	public boolean containsCustomCommand(String command) {
		return getAllCustomCommands().contains(command);
	}

	
	/**
	 * Gets a mapping of all variables and their values.
	 * @return Mapping of all variables and their values
	 * @throws BackendException Any exception while fetching the mapping.
	 */
	public abstract Map<String, Double> getAllVariablesAndValues()
			throws BackendException;

	/**
	 * Gets all user defined custom commands
	 * @return List of user commands.
	 */
	public abstract List<String> getAllCustomCommands();
}
