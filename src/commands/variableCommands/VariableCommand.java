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
import commands.information.IVariableContainer;

public abstract class VariableCommand extends BaseCommand{

    private SlogoView myView;
    private Turtle myTurtle;
    private IVariableContainer myVariableContainer;
    
    public VariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException{
        myView = view;
        myTurtle = turtle;
        myVariableContainer = variableContainer;
        double result = execute(variableContainer);
        if(getNextCommand() != null){
            return getNextCommand().execute(view, turtle, variableContainer);
        }
        return result;
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
		boolean extendsVariableContainer = IVariableContainer.class.isAssignableFrom(container.getClass());
		if(!extendsVariableContainer) {
			//throw exception
		}
		IVariableContainer variableContainer = (IVariableContainer)container;
		myVariableContainer = variableContainer;
	}
	
    protected double executeCommand(BaseCommand command, IVariableContainer variableContainer) throws BackendException{
        return command.execute(myView, myTurtle, myVariableContainer);
    }
    
    protected IVariableContainer getVariableContainer(){
        return myVariableContainer;
    }
    
    protected abstract double execute(IVariableContainer variableContainer) throws BackendException;
}
