package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import commandParser.CommandFactory;
import commands.information.BaseGridContainer;
import commands.information.IInformationContainer;
import backendExceptions.BackendException;

/**
 * View command that extends BaseCommand, and whose sole purpose is to update
 * the view.
 */
public abstract class ViewCommand extends BaseCommand {

	private BaseGridContainer myGridContainer;
	private BaseCommand[] myArgumentList;

	public ViewCommand (String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	// public abstract void updateTurtle(Turtle turtle);

	@Override
	public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
		Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
		typeSet.add(BaseGridContainer.class);
		return typeSet;
	}

	public void setRequiredInformation (Collection<IInformationContainer> containers) {
		if (containers.size() != 1) {
			// throw throw new BackendException
		}
		ArrayList<IInformationContainer> containerList = new ArrayList<>(containers);
		IInformationContainer container = containerList.get(0);
		boolean extendsGridContainer =
				BaseGridContainer.class.isAssignableFrom(container.getClass());
		if (!extendsGridContainer) {
			// throw exception
		}

		myGridContainer = (BaseGridContainer) container;

	}

	protected BaseGridContainer getGridContainer () {
		return myGridContainer;
	}

	@Override
	protected void parseArguments (String userInput) throws BackendException {
		int argumentCount = getArgumentCount();

		if (argumentCount < 0) {
			// TODO: make separate exception
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
	protected void reset(){}
}