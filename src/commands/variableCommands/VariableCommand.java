package commands.variableCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;

import commands.BaseCommand;
import commands.information.BaseUserDefinedContainer;
import commands.information.IInformationContainer;

public abstract class VariableCommand extends BaseCommand {

    private SlogoView myView;
    private Turtle myTurtle;
    private BaseUserDefinedContainer myVariableContainer;

    public VariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
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
	protected void reset(){}
}
