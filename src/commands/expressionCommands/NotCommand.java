package commands.expressionCommands;

import backendExceptions.BackendException;

public class NotCommand extends SingleExpressionCommand {

	public NotCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double expressionExecute() throws BackendException {
		double returnValue = executeCommand(getArgument()) == 0 ? 1 : 0;
		System.out.println("returnValue: " + returnValue);
		//        boolean statement ? true result : false result;
		return returnValue;
	}

}