package commands.expressionCommands;

import backendExceptions.BackendException;

public class NotEqualCommand extends DoubleExpressionCommand {

	public NotEqualCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	public double expressionExecute() throws BackendException {
		double returnValue = executeCommand(getFirstExpression()) != executeCommand(getSecondExpression()) ? 1 : 0;
		System.out.println("returnValue: " + returnValue);
		//        boolean statement ? true result : false result;
		return returnValue;
	}

}