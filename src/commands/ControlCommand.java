package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import backendExceptions.BackendException;
import commands.information.BaseUserDefinedContainer;
import commands.information.IInformationContainer;

/**
 * Commands for control structure. Has access to BaseUserDefinedContainer
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class ControlCommand extends BaseCommand {

    private static final String INVALID_CONTAINERS_MESSAGE = "Invalid containers received";
	protected static String COMMAND_INDICATOR = "liststart";
    protected static String COMMAND_END_INDICATOR = "listend";
    protected static String COMMAND_SEPARATOR = " ";
    protected static String VARIABLE_INDICATOR = "variable";
    private BaseUserDefinedContainer myVariableContainer;

    public ControlCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
        Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
        typeSet.add(BaseUserDefinedContainer.class);
        return typeSet;
    }

    public void setRequiredInformation (Collection<IInformationContainer> containers) throws BackendException {
        if (containers.size() != 1) {
            throw new BackendException(null, INVALID_CONTAINERS_MESSAGE);
        }
        ArrayList<IInformationContainer> containerList = new ArrayList<>(containers);
        IInformationContainer container = containerList.get(0);
        boolean extendsVariableContainer = BaseUserDefinedContainer.class.isAssignableFrom(container
                .getClass());
        if (!extendsVariableContainer) {
        	throw new BackendException(null, INVALID_CONTAINERS_MESSAGE);
        }
        BaseUserDefinedContainer variableContainer = (BaseUserDefinedContainer) container;
        myVariableContainer = variableContainer;
    }

    protected BaseUserDefinedContainer getVariableContainer () {
        return myVariableContainer;
    }

    @Override
    protected void reset () {
        
    }
}
