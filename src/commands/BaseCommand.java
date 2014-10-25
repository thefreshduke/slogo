package commands;

import java.util.Collection;
import java.util.Set;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.information.IInformationContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 * 
 * Abstract superclass for all SLOGO commands. The execute method 
 * returns the turtle object that can be manipulated by other commands. All other
 * types of commands will extend the BaseCommand, such as commands that only
 * modify the view, commands that are conditional, mathematical operations.
 *
 */
public abstract class BaseCommand {
	private BaseCommand myNextCommand;
	private BaseCommand myInternalCommand;
	private String myLeftoverString = "";
	private boolean myExpressionFlag;
	protected final String COMMAND_DELIMITER = "\\s+";

	/**
	 * @param userInput
	 * @throws BackendException TODO
	 */
	public BaseCommand (String userInput, boolean isExpression) throws BackendException {
		myExpressionFlag = isExpression;
		parseArguments(userInput);
	}

	/**
	 * Method returns the computation of the turtle command
	 * 
	 * @throws BackendException TODO
	 * 
	 */

	public double execute() throws BackendException {
		double result = onExecute();
		if (getNextCommand() != null) {
			return getNextCommand().execute();
		}
		return result;
	}

	protected abstract double onExecute () throws BackendException;

	public abstract Set<Class<? extends IInformationContainer>> getRequiredInformationTypes ();

	public abstract void setRequiredInformation (Collection<IInformationContainer> containers);

	private BaseCommand getNextCommand () {
		return myNextCommand;
	}

	protected abstract void parseArguments (String userInput) throws BackendException;

	public String getLeftoverString () {
		return myLeftoverString;
	}

	protected void setLeftoverCommands (String string) {
		if (myExpressionFlag) {
			myLeftoverString = string;
		}
		else if (string != null && string != "") {
			myNextCommand = CommandFactory.createCommand(string, false);
		}
	}
}
