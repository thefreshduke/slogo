package commands.controlCommands;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import commands.information.IVariableContainer;


public class DoCommand extends ControlCommand {
    private String myVariableName;
    private BaseCommand myLimitCommand;
    private BaseCommand myInternalCommand;

    public DoCommand (String userInput, boolean isExpression)
                                                             throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double execute (IVariableContainer variableContainer) throws BackendException {
        double returnValue = 0;
        double expressionResult = executeCommand(myLimitCommand, variableContainer);

        if (expressionResult < 1) {
            returnValue = 0;
        }
        else {
            int repetitionCount = (int) expressionResult;

            variableContainer.addVariable(myVariableName, repetitionCount);
            for (int i = 1; i < repetitionCount; i++) {
                returnValue = executeCommand(myInternalCommand, variableContainer);
            }
        }
        if (getNextCommand() != null) {
            returnValue = executeCommand(getNextCommand(), variableContainer);
        }
        return returnValue;
    }

    @Override
    protected void parseArguments (String userInput) {
        String[] splitInput = splitByInnerListCommand(userInput);
        String innerInput = splitInput[0];

        String[] innerArguments = innerInput.split(COMMAND_SEPARATOR, 2);
        if (innerArguments.length < 2) {
            // TODO throw exception
        }
        myVariableName = innerArguments[0];
        myLimitCommand = CommandFactory.createCommand(innerArguments[1], true);

        // Now matching the second set of braces to get command
        String[] secondSplitInput = splitByInnerListCommand(splitInput[1]);
        String innerCommand = secondSplitInput[0];
        myInternalCommand = CommandFactory.createCommand(innerCommand, false);
        setLeftoverCommands(secondSplitInput[1]);
    }
}
