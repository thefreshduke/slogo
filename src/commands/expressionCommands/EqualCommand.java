package commands.expressionCommands;

import backendExceptions.BackendException;

public class EqualCommand extends DoubleExpressionCommand {
	
	public EqualCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
    public double expressionExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) == executeCommand(getSecondExpression()) ? 1 : 0;
        System.out.println("returnValue: " + returnValue);
//        boolean statement ? true result : false result;
        return returnValue;
    }

}