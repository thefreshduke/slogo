package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import commands.information.BaseGridContainer;
import commands.information.BaseVariableContainer;
import commands.information.IInformationContainer;
import commands.information.BaseVariableContainer;
import backendExceptions.BackendException;
import turtle.Turtle;

/**
 * View command that extends BaseCommand, and whose sole purpose is to update 
 * the view.
 */
public abstract class ViewCommand extends BaseCommand {

	private BaseGridContainer myGridContainer;
	
	public ViewCommand(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	public abstract void updateTurtle(Turtle turtle);

    @Override
	public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes(){
		Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
		typeSet.add(BaseGridContainer.class);
		return typeSet;
	}
	
	public void setRequiredInformation(Collection<IInformationContainer> containers){
		if(containers.size() != 1){
			//throw throw new BAckendException
		}
		ArrayList<IInformationContainer> containerList = new ArrayList<>(containers);
		IInformationContainer container = containerList.get(0);
		boolean extendsGridContainer = BaseGridContainer.class.isAssignableFrom(container.getClass());
		if(!extendsGridContainer) {
			//throw exception
		}
		BaseGridContainer variableContainer = (BaseGridContainer)container;
		myGridContainer = variableContainer;
	}
	
	protected BaseGridContainer getGridContainer() {
		return myGridContainer;
	}
}
