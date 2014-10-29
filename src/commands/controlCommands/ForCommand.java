package commands.controlCommands;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.BaseUserDefinedContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class ForCommand extends ControlCommand {

    private static final String INVALID_COMMAND_ENTERED = "Invalid command entered";
    private static final String INSUFFICIENT_COMMANDS_ENTERED = "Insufficient commands entered";

    private String myVariableName;
    private BaseCommand myStartCommand;
    private BaseCommand myEndCommand;
    private BaseCommand myIncrementCommand;
    private BaseCommand myInternalCommand;

    private BaseUserDefinedContainer myVariableContainer;
    private boolean myVarExistsPreviously;

    public ForCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        myVariableContainer = getVariableContainer();
        double returnValue = 0;
        int startValue = (int)myStartCommand.execute();
        int stopValue = (int)myEndCommand.execute();
        int incrementValue = (int)myIncrementCommand.execute();
        myVarExistsPreviously = false;
        BaseCommand oldCommand = null;
        if (myVariableContainer.containsVariable(myVariableName)) {
            oldCommand = myVariableContainer.getValue(myVariableName);
            myVarExistsPreviously = true;
        }
        for (int i = startValue; i < stopValue; i += incrementValue) {
            myVariableContainer.addVariable(myVariableName, i);
            returnValue = myInternalCommand.execute();
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
        String innerInput = splitInput[0];
        String[] variableNameContents = innerInput.split(VARIABLE_INDICATOR, 2);
        if (variableNameContents.length < 2) {
            throw new BackendException(null, INSUFFICIENT_COMMANDS_ENTERED);
        }

        myVariableName = variableNameContents[1].trim().split(COMMAND_SEPARATOR)[0];

        if (myVariableName.equals("")) {
            throw new BackendException(null, INVALID_COMMAND_ENTERED);
        }

        myStartCommand = CommandFactory.createCommand(
                variableNameContents[1].trim().split(COMMAND_SEPARATOR, 2)[1].trim(), true);
        myEndCommand = CommandFactory.createCommand(myStartCommand.getLeftoverString(), true);
        myIncrementCommand = CommandFactory.createCommand(myEndCommand.getLeftoverString(), true);

        // Now matching the second set of braces to get internal commands
        String[] splitSecondInput = splitByInnerListCommand(splitInput[1]);
        myInternalCommand = CommandFactory.createCommand(splitSecondInput[0], false);

        setLeftoverCommands(splitSecondInput[1]);
    }
}
