package commands.booleanCommands;

import commands.mathCommands.DoubleExpressionCommand;

import backendExceptions.BackendException;

public class OrCommand extends DoubleExpressionCommand {
	
	public OrCommand(String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
    public double expressionExecute() throws BackendException {
        double returnValue = (executeCommand(getFirstExpression()) != 0 || executeCommand(getSecondExpression()) != 0) ? 1 : 0;
        System.out.println("returnValue: " + returnValue);
//        boolean statement ? true result : false result;
        return returnValue;
    }

}
