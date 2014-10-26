package commands.information;

import java.util.List;
import java.util.Map;

import backendExceptions.BackendException;
import commands.BaseCommand;

public abstract class BaseVariableContainer implements IInformationContainer {

    public abstract boolean addVariable (String variable, BaseCommand command)
            throws BackendException;

    public abstract boolean addVariable (String variable, double value) throws BackendException;

    public abstract BaseCommand getValue (String variable) throws BackendException;

    public abstract BaseCommand popOffVariable (String variable);

    public abstract void addNewCommand (String commandName, String innerCommands,
            String[] temporaryVariables);

    public abstract BaseCommand getCreatedCommand (String commandName, String input,
            boolean isExpression) throws BackendException;

    public abstract boolean containsVariable (String variable);
    
    public boolean containsCustomCommand(String command){
    	return getAllCustomCommands().contains(command);
    }
    
    public abstract Map<String, Double> getAllVariablesAndValues() throws BackendException;
    
    public abstract List<String> getAllCustomCommands();
}
