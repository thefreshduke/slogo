package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import backendExceptions.BackendException;

import commands.information.BaseUserDefinedContainer;
import commands.information.IInformationContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class ControlCommand extends BaseCommand {

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

    public void setRequiredInformation (Collection<IInformationContainer> containers) {
        if (containers.size() != 1) {
            // throw throw new BAckendException
        }
        ArrayList<IInformationContainer> containerList = new ArrayList<>(containers);
        IInformationContainer container = containerList.get(0);
        boolean extendsVariableContainer = BaseUserDefinedContainer.class.isAssignableFrom(container
                .getClass());
        if (!extendsVariableContainer) {
            // throw exception
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
