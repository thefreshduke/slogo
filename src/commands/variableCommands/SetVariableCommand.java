package commands.variableCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.information.BaseUserDefinedContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class SetVariableCommand extends VariableCommand {

    private static final String INVALID_VARIABLE_NAME_MESSAGE = "Invalid variable name";
	private String myVariableName;
    private BaseCommand myExpression;
    private final int USER_INPUT_SPLIT_COUNT = 3;
    private final String VARIABLE_INDICATOR = "variable";
    
    public SetVariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        double expressionResult = myExpression.execute();
        BaseUserDefinedContainer container = getVariableContainer();
        container.addVariable(myVariableName, expressionResult);
        return expressionResult;
    }

    @Override
    protected void parseArguments (String userInput) throws BackendException {
        String[] inputList = userInput.trim().split(COMMAND_DELIMITER, USER_INPUT_SPLIT_COUNT);
        if (inputList.length < USER_INPUT_SPLIT_COUNT || !inputList[0].trim().equals(VARIABLE_INDICATOR)) {
            throw new BackendException(null, INVALID_VARIABLE_NAME_MESSAGE);
        }
        myVariableName = inputList[1].trim();
        String leftOverString = inputList[2].trim();
        myExpression = CommandFactory.createCommand(leftOverString, true);
        setLeftoverCommands(myExpression.getLeftoverString());
    }
}
