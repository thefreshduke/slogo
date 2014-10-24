package commands.variableCommands;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

=======
import model.CommandWrapper;
>>>>>>> b68372fa2d93ef21b91af967c286ca714bf2bcf3
import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.information.BaseVariableContainer;
import commands.information.IInformationContainer;
import commands.information.IVariableContainer;

public abstract class VariableCommand extends BaseCommand{

    private CommandWrapper myWrapper;
    
    public VariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute(CommandWrapper wrapper) throws BackendException{
    	myWrapper = wrapper;
        double result = execute(wrapper.getVariableContainer());
        if(getNextCommand() != null){
            return getNextCommand().execute(wrapper);
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
        return command.execute(myWrapper);
    }
    
    protected IVariableContainer getVariableContainer(){
        return myWrapper.getVariableContainer();
    }
    
    protected abstract double execute(IVariableContainer variableContainer) throws BackendException;
}
