package commands.booleanCommands;

import backendExceptions.BackendException;

public class EqualCommand extends DoubleExpressionBooleanCommand {
	
	public EqualCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
    public double booleanExecute() throws BackendException {
        double returnValue = executeCommand(getFirstExpression()) == executeCommand(getSecondExpression()) ? 1 : 0;
        System.out.println("returnValue: " + returnValue);
//        boolean statement ? true result : false result;
        return returnValue;
    }

}
