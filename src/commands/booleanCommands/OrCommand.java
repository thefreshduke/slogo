package commands.booleanCommands;

import backendExceptions.BackendException;

public class OrCommand extends DoubleExpressionBooleanCommand {
	
	public OrCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
    public double booleanExecute() throws BackendException {
        double returnValue = (executeCommand(getFirstExpression()) != 0 || executeCommand(getSecondExpression()) != 0) ? 1 : 0;
        System.out.println("returnValue: " + returnValue);
//        boolean statement ? true result : false result;
        return returnValue;
    }

}
