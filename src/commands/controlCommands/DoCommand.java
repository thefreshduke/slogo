package commands.controlCommands;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.BaseVariableContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class DoCommand extends ControlCommand {

    private static final String INVALID_COMMAND_ENTERED = "Invalid command entered";
    private static final String INSUFFICIENT_COMMANDS_ENTERED = "Insufficient commands entered";

    private String myVariableName;
    private BaseCommand myLimitCommand;
    private BaseCommand myInternalCommand;

    private BaseVariableContainer myVariableContainer;
    private boolean myVarExistsPreviously;

    public DoCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        myVariableContainer = getVariableContainer();
        double returnValue = 0;
        double expressionResult = myLimitCommand.execute();
        myVarExistsPreviously = false;
        BaseCommand oldCommand = null;
        if (myVariableContainer.containsVariable(myVariableName)) {
            oldCommand = myVariableContainer.getValue(myVariableName);
            myVarExistsPreviously = true;
        }
        if (expressionResult < 1) {
            returnValue = 0;
        }
        else {
            int repetitionCount = (int)expressionResult;

            for (int i = 1; i <= repetitionCount; i++) {
                myVariableContainer.addVariable(myVariableName, i);
                returnValue = myInternalCommand.execute();
            }
        }
        checkIfVariableExistsPreviously(oldCommand);
        return returnValue;
    }

    private void checkIfVariableExistsPreviously (BaseCommand oldCommand) throws BackendException {
        if (myVarExistsPreviously) {
            myVariableContainer.addVariable(myVariableName, oldCommand);
        }
        else {
            myVariableContainer.popOffVariable(myVariableName);
        }
    }

    @Override
    protected void parseArguments (String userInput) throws BackendException {
        String[] splitInput = splitByInnerListCommand(userInput);
        String[] variableNameContents = splitInput[0].split(VARIABLE_INDICATOR);
        if (variableNameContents.length < 2) {
            throw new BackendException(null, INSUFFICIENT_COMMANDS_ENTERED);
        }
        myVariableName = variableNameContents[1].trim().split(COMMAND_SEPARATOR)[0];

        if (myVariableName.equals("")) {
            throw new BackendException(null, INVALID_COMMAND_ENTERED);
        }
        String variableExpression = splitInput[0].split(VARIABLE_INDICATOR)[1].trim();
        String variableLimit = variableExpression.replace(myVariableName, "").trim();
        myLimitCommand = CommandFactory.createCommand(variableLimit, true);

        // Now matching the second set of braces to get command
        String[] secondSplitInput = splitByInnerListCommand(splitInput[1]);
        String innerCommand = secondSplitInput[0];
        myInternalCommand = CommandFactory.createCommand(innerCommand, false);
        setLeftoverCommands(secondSplitInput[1]);
    }
}