package commands.variableCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;

import commands.BaseCommand;
import commands.information.BaseVariableContainer;
import commands.information.IInformationContainer;

public abstract class VariableCommand extends BaseCommand {

    private SlogoView myView;
    private Turtle myTurtle;
    private BaseVariableContainer myVariableContainer;

    public VariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
        Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
        typeSet.add(BaseVariableContainer.class);
        return typeSet;
    }

    public void setRequiredInformation (Collection<IInformationContainer> containers) {
        if (containers.size() != 1) {
            // throw throw new BAckendException
        }
        ArrayList<IInformationContainer> containerList = new ArrayList<>(containers);
        IInformationContainer container = containerList.get(0);
        boolean extendsVariableContainer = BaseVariableContainer.class.isAssignableFrom(container
                .getClass());
        if (!extendsVariableContainer) {
            // throw exception
        }
        BaseVariableContainer variableContainer = (BaseVariableContainer) container;
        myVariableContainer = variableContainer;
    }

    protected BaseVariableContainer getVariableContainer () {
        return myVariableContainer;
    }
    
    @Override
	protected void reset(){}
}
