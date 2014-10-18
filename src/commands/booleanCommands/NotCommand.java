package commands.booleanCommands;

import backendExceptions.BackendException;

public class NotCommand extends SingleExpressionBooleanCommand {
	
	public NotCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
    public double booleanExecute() throws BackendException {
        double returnValue = executeCommand(getArgument()) == 0 ? 1 : 0;
        System.out.println("returnValue: " + returnValue);
//        boolean statement ? true result : false result;
        return returnValue;
    }

}
