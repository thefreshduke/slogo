package commands.MultiTurtleCommand;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import backendExceptions.BackendException;

import commands.BaseCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.information.IInformationContainer;

public abstract class MultiTurtleCommand extends BaseCommand {

    public MultiTurtleCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
        HashSet<Class<? extends IInformationContainer>> requiredTypes = new HashSet<>();
        requiredTypes.add(BaseTurtleContainer.class);
        requiredTypes.add(BaseGridContainer.class);
        return requiredTypes;
    }

    @Override
    public void setRequiredInformation (Collection<IInformationContainer> containers) {

    }

}
