package commands.information;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import backendExceptions.BackendException;
import commands.BaseCommand;

public abstract class BaseVariableContainer implements IInformationContainer{

    public abstract boolean addVariable(String variable, BaseCommand command) throws BackendException;
    
    public abstract boolean addVariable(String variable, double value) throws BackendException;
    
    public abstract BaseCommand getValue(String variable) throws BackendException;
    
    public abstract BaseCommand popOffVariable(String variable);
    
    public abstract void addNewCommand(String commandName, String innerCommands, String[] temporaryVariables);
    
    public abstract BaseCommand getCreatedCommand(String commandName, String input, boolean isExpression) throws BackendException;

    public abstract boolean containsVariable (String variable);
}
