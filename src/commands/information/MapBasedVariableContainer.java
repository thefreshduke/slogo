package commands.information;

import java.util.HashMap;
import java.util.Map;
import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.NumericalCommand;
import commands.variableCommands.CreatedCommand;

public class MapBasedVariableContainer extends BaseVariableContainer{
    
    private Map<String, BaseCommand> myVariableToCommandMap;
    private Map<String, String> myCreatedCommandMap;
    private Map<String, String[]> myCreatedCommanVariableMap;
    
    public MapBasedVariableContainer () {
        myVariableToCommandMap = new HashMap<>();
        myCreatedCommandMap = new HashMap<>();
        myCreatedCommanVariableMap = new HashMap<>();
    }
    
    @Override
    public boolean addVariable (String variable, BaseCommand command) throws BackendException{
        if(command == null || variable == null || variable.equals("")){
            throw new BackendException(null, "Invalid variable or command provided");
        }
        try{
            myVariableToCommandMap.put(variable, command);
            return true;
        }
        catch (Exception ex){
            throw new BackendException(null, "Unexpected error when trying to set variable");
        }
    }

    @Override
    public boolean containsVariable(String variable){
        return myVariableToCommandMap.containsKey(variable);
    }
    
    @Override
    public boolean addVariable (String variable, double value) throws BackendException{
        NumericalCommand number = new NumericalCommand(((Double)value).toString(), true);
        return addVariable(variable, number);
    }

    @Override
    public BaseCommand getValue (String variable) throws BackendException {
        if(myVariableToCommandMap.containsKey(variable)){
            return myVariableToCommandMap.get(variable);
        }
        else{
            return new NumericalCommand(((Double)0.0).toString(), true);
        }
    }

    @Override
    public BaseCommand popOffVariable (String variable) {
        if(myVariableToCommandMap.containsKey(variable)){
            BaseCommand value = myVariableToCommandMap.get(variable);
            myVariableToCommandMap.remove(variable);
            return value;
        }
        return null;
    }

    @Override
    public void addNewCommand (String commandName, String innerCommands, String[] temporaryVariables) {
        myCreatedCommandMap.put(commandName, innerCommands);
        myCreatedCommanVariableMap.put(commandName, temporaryVariables);
    }

    @Override
    public BaseCommand getCreatedCommand (String commandName, String input, boolean isExpression) throws BackendException{
        String[] variables = myCreatedCommanVariableMap.get(commandName);
        BaseCommand[] correspondingExpressions = new BaseCommand[variables.length];
        String subInput = input;
        for(int i= 0; i < variables.length; i++){
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
}
