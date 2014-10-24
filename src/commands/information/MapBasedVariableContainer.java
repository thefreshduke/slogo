package commands.information;

import java.util.HashMap;
import java.util.Map;
import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.NumericalCommand;

public class MapBasedVariableContainer implements IVariableContainer, IInformationContainer{
    
    private Map<String, BaseCommand> myVariableToCommandMap;
    private Map<String, BaseCommand> myCreatedCommandMap;
    
    public MapBasedVariableContainer () {
        myVariableToCommandMap = new HashMap<>();
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public BaseCommand getCreatedCommand (String commandName, String input) {
        // TODO Auto-generated method stub
        return null;
    }
}
