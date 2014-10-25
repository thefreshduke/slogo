package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import commands.information.BaseVariableContainer;
import commands.information.IInformationContainer;
import backendExceptions.BackendException;

public abstract class ControlCommand extends ModelCommand {

    protected static String COMMAND_INDICATOR = "liststart";
    protected static String COMMAND_END_INDICATOR = "listend";
    protected static String COMMAND_SEPARATOR = " ";
    protected static String VARIABLE_INDICATOR = "variable";
    private BaseVariableContainer myVariableContainer;

    public ControlCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes(){
        Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
        typeSet.add(BaseVariableContainer.class);
        return typeSet;
    }

    public void setRequiredInformation(Collection<IInformationContainer> containers){
        if(containers.size() != 1){
            //throw throw new BAckendException
        }
        ArrayList<IInformationContainer> containerList = new ArrayList<>(containers);
        IInformationContainer container = containerList.get(0);
        boolean extendsVariableContainer = BaseVariableContainer.class.isAssignableFrom(container.getClass());
        if(!extendsVariableContainer) {
            //throw exception
        }
        BaseVariableContainer variableContainer = (BaseVariableContainer)container;
        myVariableContainer = variableContainer;
    }

    protected BaseVariableContainer getVariableContainer(){
        return myVariableContainer;
    }
    
    @Override
	protected void reset(){}
}
