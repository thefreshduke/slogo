package commands.viewCommands;

import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.BaseGridContainer;
import commands.information.BaseTurtleContainer;
import commands.turtleCommands.TurtleCommand;

public class ShowTurtleCommand extends TurtleCommand {

    public ShowTurtleCommand (String command, boolean isExpression) throws BackendException {
        super(command, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
    	  BaseTurtleContainer turtle = getTurtleContainer();
          turtle.setVisibility(true);
          return 1;
    }

    @Override
    protected int getArgumentCount () {
        return 0;
    }
}
