// This entire file is part of my masterpiece.
// Rahul Harikrishnan
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
 * Abstract TurtleCommand class that can be extended for any Turtle commands.  The parsing method 
 * can be overridden if necessary, but the onExecute() and getArgumentCount() abstract
 * methods must be implemented in any TurtleCommand extending subclass.
 * @author Rahul
 *
 */
public abstract class TurtleCommand extends BaseCommand {
	private static final String NULL_CONTAINER = "One or more containers are null and not set";
	private static final String INSUFFICIENT_CONTAINERS_PROVIDED = "Insufficient containers provided";
	private static final String INSUFFICIENT_ARGUMENTS_PROVIDED = "Insufficient arguments provided";
	private static final int NUM_CONTAINERS = 2;
	private BaseCommand[] myArgumentList;
	private BaseGridContainer myGridContainer;
	private BaseTurtleContainer myTurtleContainer;

	public TurtleCommand (String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	/**
	 * Gets the required IInformationContainer objects for all Turtle Commands, to ensure that 
	 * only the necessary container objects are accessed by Turtle subclasses.  This method
	 * is called from the CommandFactory upon creation of the specific Command class object to get
	 * the appropriate container types. 
	 */
	@Override
	public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes () {
		Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
		typeSet.add(BaseTurtleContainer.class);
		typeSet.add(BaseGridContainer.class);
		return typeSet;
	}

	/**
	 * Set the required container types for the TurtleCommand via checking class types, as well
	 * as throws exceptions if containers are not properly set.
	 */
	@Override
	public void setRequiredInformation (Collection<IInformationContainer> containers) 
			throws BackendException {
		if (containers.size() != NUM_CONTAINERS) 
			throw new BackendException(null, INSUFFICIENT_CONTAINERS_PROVIDED);

		for (IInformationContainer container : containers) {
			if (BaseGridContainer.class.isAssignableFrom(container.getClass())) 
				myGridContainer = (BaseGridContainer)container;

			else if (BaseTurtleContainer.class.isAssignableFrom(container.getClass()))
				myTurtleContainer = (BaseTurtleContainer)container;
		}
		// Check to see if any of the containers are null after assigning them. Might seem logical
		// to move this to the top. However, would always throw an exception if placed before 
		// container assignment.
		if (myGridContainer == null || myTurtleContainer == null) 
			throw new BackendException(null, NULL_CONTAINER);

	}

	/**
	 * Returns the BaseTurtleContainer object that is used by the commands to 
	 * manipulate the location of turtles (movement and position-setting). 
	 * @return BaseTurtleContainer
	 */
	protected BaseTurtleContainer getTurtleContainer () {
		return myTurtleContainer;
	}

	/**
	 * Returns the BaseGridContainer object that is used by the commands to 
	 * perform manipulations on the grid, such as updating the locations
	 * of the active turtles
	 * @return BaseGridContainer
	 */
	protected BaseGridContainer getGridContainer () {
		return myGridContainer;
	}

	/**
	 * Parsing algorithm that creates BaseCommand object for each expression
	 * using the number of expressions (getExpressionCount()) for the command.
	 */
	@Override
	protected void parseArguments (String userInput) throws BackendException {
		int argumentCount = getExpressionCount();
		
		if (argumentCount < 0) 
			throw new BackendException(null, INSUFFICIENT_ARGUMENTS_PROVIDED);
		
		myArgumentList = new BaseCommand[argumentCount];

		String subInput = "";
		for (int i = 0; i < argumentCount; i++) {
			subInput = (i == 0)  ? userInput : myArgumentList[i - 1].getLeftoverString();
			BaseCommand argument = CommandFactory.createCommand(subInput, true);
			myArgumentList[i] = argument;
		}

		if (argumentCount == 0) 
			setLeftoverCommands(userInput);
		else 
			setLeftoverCommands(myArgumentList[myArgumentList.length - 1].getLeftoverString());
	}


	/**
	 * Returns the list of expressions returned as BaseCommand objects which can 
	 * be executed to produce doubles used in computing a result.
	 * @return array of BaseCommand expressions
	 */
	protected BaseCommand[] getExpressionList () {
		return myArgumentList;
	}

	/**
	 * Gets the argument count/number of expressions that the command takes. This is used to 
	 * create the appropriate number of expressions for the given command.
	 * @return argument count
	 */
	protected abstract int getExpressionCount ();

	/**
	 * If commands were to modified during execution, this reset method would have
	 * implementation details that reverts the command to its pre-execution state.
	 */
	@Override
	protected void reset () { }
}