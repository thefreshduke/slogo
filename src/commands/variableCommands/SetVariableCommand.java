package commands.variableCommands;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.information.BaseUserDefinedContainer;

public class SetVariableCommand extends VariableCommand {

    private String myVariableName;
    private BaseCommand myExpression;

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
        String[] inputList = userInput.trim().split(COMMAND_DELIMITER, 2);
        if (inputList.length < 2) {
            throw new BackendException(null, "Invalid variable name");
        }
        myVariableName = inputList[0];
        String leftOverString = inputList[1].trim();
        myExpression = CommandFactory.createCommand(leftOverString, true);
        setLeftoverCommands(myExpression.getLeftoverString());
    }
}
