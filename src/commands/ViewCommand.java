package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.information.BaseGridContainer;
import commands.information.IInformationContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 * View command that extends BaseCommand, and whose sole purpose is to update
 * the view.
 */
public abstract class ViewCommand extends BaseCommand {

    private static final String INVALID_EXTENSION_OF_GRID_CONTAINER = "Invalid extension of Grid Container";
    private static final String INVALID_CONTAINERS_RECEIVED = "Invalid containers received";
    private static final String INVALID_NUMBER_OF_ARGUMENTS_PROVIDED = "Invalid number of arguments provided";
	private BaseGridContainer myGridContainer;
    private BaseCommand[] myArgumentList;

    public ViewCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
        Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
        typeSet.add(BaseGridContainer.class);
        return typeSet;
    }

    public void setRequiredInformation (Collection<IInformationContainer> containers) throws BackendException {
        if (containers.size() != 1) {
            throw new BackendException(null, INVALID_CONTAINERS_RECEIVED);
        }
        ArrayList<IInformationContainer> containerList = new ArrayList<>(containers);
        IInformationContainer container = containerList.get(0);
        boolean extendsGridContainer = BaseGridContainer.class.isAssignableFrom(container
                .getClass());
        if (!extendsGridContainer) {
            throw new BackendException(null, INVALID_EXTENSION_OF_GRID_CONTAINER);
        }
        myGridContainer = (BaseGridContainer)container;
    }

    protected BaseGridContainer getGridContainer () {
        return myGridContainer;
    }

    @Override
    protected void parseArguments (String userInput) throws BackendException {
        int argumentCount = getArgumentCount();

        if (argumentCount < 0) {
        	throw new BackendException(null, INVALID_NUMBER_OF_ARGUMENTS_PROVIDED);
        }

        if (argumentCount == 0) {
            // Nothing more to parse for this command
            return;
        }

        myArgumentList = new BaseCommand[argumentCount];
        for (int i = 0; i < argumentCount; i++) {
            String subInput;
            if (i == 0) {
                subInput = userInput;
            }
            else {
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

    @Override
    protected void reset () {

    }
}