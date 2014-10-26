package commands.variableCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import backendExceptions.BackendException;

import commands.BaseCommand;
import commands.information.BaseUserDefinedContainer;

public class CreatedCommand extends VariableCommand {

    private Map<String, BaseCommand> myNewVariableToCommandMap;
    private BaseCommand myInnerCommand;
    private Map<String, BaseCommand> myOldExistingVariableMap;
    private Collection<String> myNonExistingVariables;

    public CreatedCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        myNewVariableToCommandMap = new HashMap<>();
        myOldExistingVariableMap = new HashMap<>();
        myNonExistingVariables = new ArrayList<>();
    }

    @Override
    protected double onExecute () throws BackendException {
        setNewVariables();
        double result = myInnerCommand.execute();
        revertVariables();
        return result;
    }

    private void setNewVariables () throws BackendException {
        Set<String> variableSet = myNewVariableToCommandMap.keySet();
        BaseUserDefinedContainer variableContainer = getVariableContainer();
        for (String variable : variableSet) {
            if (variableContainer.containsVariable(variable)) {
                BaseCommand oldCommand = variableContainer.getValue(variable);
                myOldExistingVariableMap.put(variable, oldCommand);
            } else {
                myNonExistingVariables.add(variable);
            }
            double newCommandResult = myNewVariableToCommandMap.get(variable).execute();
            variableContainer.addVariable(variable, newCommandResult);
        }
    }

    private void revertVariables () throws BackendException {
        BaseUserDefinedContainer variableContainer = getVariableContainer();
        for (String variable : myNonExistingVariables) {
            variableContainer.popOffVariable(variable);
        }
        for (String variable : myOldExistingVariableMap.keySet()) {
            BaseCommand oldCommand = myOldExistingVariableMap.get(variable);
            variableContainer.addVariable(variable, oldCommand);
        }
    }

    public void setVariables (String[] variables, BaseCommand[] variableValues) {
        for (int i = 0; i < variables.length; i++) {
            myNewVariableToCommandMap.put(variables[i], variableValues[i]);
        }
    }

    public void setInnerCommand (BaseCommand innerCommand) {
        myInnerCommand = innerCommand;
    }

    @Override
    protected void parseArguments (String userInput) throws BackendException {
        setLeftoverCommands(userInput);
    }

}
