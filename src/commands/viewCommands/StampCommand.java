package commands.viewCommands;

import backendExceptions.BackendException;
import commands.ViewQuery;
import commands.information.BaseGridContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 */
public class StampCommand extends ViewQuery {
    private static final String STAMP_TURTLE = "stampTurtle";
    
	public StampCommand(String command, boolean isExpression)
			throws BackendException {
		super(command, isExpression);
	}

	@Override
	protected double onExecute() throws BackendException {
		BaseGridContainer grid = getGridContainer();
        grid.updateDisplayOptions(STAMP_TURTLE);
        // Would need to have the updateDisplayOptions return a double, currently does not. 
        // Could be worked-around with a special case function, but not done as this would 
        // compromise design. Hence, hard-coded return double.
        return 1;
	}

}
