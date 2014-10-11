package commands;

import backendExceptions.BackendException;

/**
 * 
 * 
 */
public abstract class ModelCommand extends BaseCommand {

	public ModelCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

}
