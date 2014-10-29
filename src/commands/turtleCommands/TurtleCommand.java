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

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public abstract class TurtleCommand extends BaseCommand {
	private static final String INSUFFICIENT_ARGUMENTS_PROVIDED = "Insufficient arguments provided";
	private static final int NUM_TURTLE_CONTAINERS = 2;
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
		if (containers.size() != NUM_TURTLE_CONTAINERS) {
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

	@Override
	protected void parseArguments (String userInput) throws BackendException {
		int argumentCount = getArgumentCount();
		if (argumentCount < 0) {
			throw new BackendException(null, INSUFFICIENT_ARGUMENTS_PROVIDED);
		} 
		if (argumentCount == 0) {
			setLeftoverCommands(userInput);
			return;
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

	@Override
	protected void reset(){}
}