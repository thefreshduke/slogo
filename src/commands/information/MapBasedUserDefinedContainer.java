package commands.information;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.NumericalCommand;
import commands.variableCommands.CreatedCommand;

public class MapBasedUserDefinedContainer extends BaseUserDefinedContainer {

    private Map<String, BaseCommand> myVariableToValuesMap;
    private Map<String, String> myCreatedCommandMap;
    private Map<String, String[]> myCreatedCommandVariableMap;

    public MapBasedUserDefinedContainer () {
        myVariableToValuesMap = new HashMap<>();
        myCreatedCommandMap = new HashMap<>();
        myCreatedCommandVariableMap = new HashMap<>();
    }

    @Override
    public boolean addVariable (String variable, BaseCommand command) throws BackendException {
        if (command == null || variable == null || variable.equals("")) {
            throw new BackendException(null, "Invalid variable or command provided");
        }
        try {
            myVariableToValuesMap.put(variable, command);
            return true;
        } catch (Exception ex) {
            throw new BackendException(null, "Unexpected error when trying to set variable");
        }
    }

    @Override
    public boolean containsVariable (String variable) {
        return myVariableToValuesMap.containsKey(variable);
    }

    @Override
    public boolean addVariable (String variable, double value) throws BackendException {
        NumericalCommand number = new NumericalCommand(((Double) value).toString(), true);
        return addVariable(variable, number);
    }

    @Override
    public BaseCommand getValue (String variable) throws BackendException {
        if (myVariableToValuesMap.containsKey(variable)) {
            return myVariableToValuesMap.get(variable);
        } else {
            return new NumericalCommand(((Double) 0.0).toString(), true);
        }
    }

    @Override
    public BaseCommand popOffVariable (String variable) {
        if (myVariableToValuesMap.containsKey(variable)) {
            BaseCommand value = myVariableToValuesMap.get(variable);
            myVariableToValuesMap.remove(variable);
            return value;
        }
        return null;
    }

    @Override
    public void addNewCommand (String commandName, String innerCommands, String[] temporaryVariables) {
        myCreatedCommandMap.put(commandName, innerCommands);
        myCreatedCommandVariableMap.put(commandName, temporaryVariables);
    }

    @Override
    public BaseCommand getCreatedCommand (String commandName, String input, boolean isExpression)
            throws BackendException {
        String[] variables = myCreatedCommandVariableMap.get(commandName);
        BaseCommand[] correspondingExpressions = new BaseCommand[variables.length];
        String subInput = input;
        for (int i = 0; i < variables.length; i++) {
            BaseCommand expression = CommandFactory.createCommand(subInput, true);
            subInput = expression.getLeftoverString();
            correspondingExpressions[i] = expression;
        }

        String innerCommandString = new String(myCreatedCommandMap.get(commandName));
        BaseCommand innerCommand = CommandFactory.createCommand(innerCommandString, false);

        CreatedCommand createdCommand = new CreatedCommand(subInput, isExpression);
        createdCommand.setInnerCommand(innerCommand);
        createdCommand.setVariables(variables.clone(), correspondingExpressions);
        return createdCommand;
    }

	@Override
	public Map<String, Double> getAllVariablesAndValues() throws BackendException {
		HashMap<String, Double> variableToComputedValueMap = new HashMap<>();
		for(String variable : myVariableToValuesMap.keySet()) {
			BaseCommand command = myVariableToValuesMap.get(variable);
			try {
				variableToComputedValueMap.put(variable, command.execute());
			} catch (BackendException e) {
				throw new BackendException(e, "There is an invalid variable in the container");
			}
		}
		return variableToComputedValueMap;
	}

	@Override
	public List<String> getAllCustomCommands() {
		return new ArrayList<>(myCreatedCommandMap.keySet());
	}
}
