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
            
            for (int i = 1; i <= repetitionCount; i++) {
                variableContainer.addVariable(myVariableName, i);
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

        if (splitInput.length < 2) {
            // TODO throw exception
        }
        
        myVariableName =  splitInput[0].split(VARIABLE_INDICATOR)[1].trim().split(COMMAND_SEPARATOR)[0];
        String  variableExpression = splitInput[0].split(VARIABLE_INDICATOR)[1].trim();
        String variableLimit = variableExpression.replace(myVariableName, "").trim();
        myLimitCommand = CommandFactory.createCommand(variableLimit, true);

        // Now matching the second set of braces to get command
        String[] secondSplitInput = splitByInnerListCommand(splitInput[1]);
        String innerCommand = secondSplitInput[0];
        myInternalCommand = CommandFactory.createCommand(innerCommand, false);
        setLeftoverCommands(secondSplitInput[1]);
    }
}