package commands.variableCommands;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.NumericalCommand;
import communicator.IVariableContainer;
import backendExceptions.BackendException;

public class MakeVariableCommand extends VariableCommand{

    private String myVariableName;
    private BaseCommand myExpression;
    
    public MakeVariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected double execute (IVariableContainer variableContainer) throws BackendException {
        double expressionResult = executeCommand(myExpression, variableContainer);
        IVariableContainer container = getVariableContainer();
        container.addVariable(myVariableName, expressionResult);
        return expressionResult;
    }

    @Override
    protected void parseArguments (String userInput) throws BackendException{
        String[] inputList = userInput.trim().split(COMMAND_DELIMITER, 2);
        if(inputList.length < 2){
            throw new BackendException(null, "Invalid variable name"); 
        }
        myVariableName = inputList[0];
        String leftOverString = inputList[1].trim();
        myExpression = CommandFactory.createCommand(leftOverString, true);
        setLeftoverCommands(myExpression.getLeftoverString()); 
    }
}
