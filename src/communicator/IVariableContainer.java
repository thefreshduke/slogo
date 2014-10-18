package communicator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import backendExceptions.BackendException;
import commands.BaseCommand;

public interface IVariableContainer {

    public boolean addVariable(String variable, BaseCommand command) throws BackendException;
    
    public boolean addVariable(String variable, double value) throws BackendException;
    
    public BaseCommand getValue(String variable) throws BackendException;
    
    public BaseCommand popOffVariable(String variable);
}
