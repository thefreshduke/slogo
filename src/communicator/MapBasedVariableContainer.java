package communicator;

import java.util.HashMap;
import java.util.Map;
import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.NumericalCommand;

public class MapBasedVariableContainer implements IVariableContainer{
    
    private Map<String, BaseCommand> myVariableToCommandMap;
    
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
        NumericalCommand number = new NumericalCommand("", value);
        return addVariable(variable, number);
    }

    @Override
    public BaseCommand getValue (String variable) throws BackendException {
        if(myVariableToCommandMap.containsKey(variable)){
            return myVariableToCommandMap.get(variable);
        }
        else{
            return new NumericalCommand("", 0);
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
}