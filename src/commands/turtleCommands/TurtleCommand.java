package commands.turtleCommands;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.information.IInformationContainer;

public abstract class TurtleCommand extends BaseCommand {
    private BaseCommand[] myArgumentList;
    private BaseGridContainer myGridContainer;
    private BaseTurtleContainer myTurtleContainer;

    public TurtleCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
        Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
        typeSet.add(BaseTurtleContainer.class);
        typeSet.add(BaseGridContainer.class);
        return typeSet;
    }

    @Override
    public void setRequiredInformation (Collection<IInformationContainer> containers) {
        if (containers.size() != 2) {
            // throw
        }
        // ArrayList<IInformationContainer> containerList = new
        // ArrayList<>(containers);
        for (IInformationContainer container : containers) {
            if (BaseGridContainer.class.isAssignableFrom(container.getClass())) {
                myGridContainer = (BaseGridContainer) container;
            } else if (BaseTurtleContainer.class.isAssignableFrom(container.getClass())) {
                myTurtleContainer = (BaseTurtleContainer) container;
            }
        }
        if (myGridContainer == null || myTurtleContainer == null) {
            // throw exception
        }
    }

    protected BaseTurtleContainer getTurtleContainer () {
        return myTurtleContainer;
    }

    protected BaseGridContainer getGridContainer () {
        return myGridContainer;
    }

    protected void parseArguments (String userInput) throws BackendException {
        int argumentCount = getArgumentCount();
        if (argumentCount < 0) {
            // TODO: make separate exception
        }
        myArgumentList = new BaseCommand[argumentCount];
        for (int i = 0; i < argumentCount; i++) {
            String subInput;
            if (i == 0) {
                subInput = userInput;
            } else {
                subInput = myArgumentList[i - 1].getLeftoverString();
            }
            BaseCommand argument = CommandFactory.createCommand(subInput, true);
            myArgumentList[i] = argument;
        }
        setLeftoverCommands(myArgumentList[myArgumentList.length - 1].getLeftoverString());
    }

    protected BaseCommand[] getExpressionList () {
        return myArgumentList;
    }

    protected abstract int getArgumentCount ();

}