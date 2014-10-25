package commands.turtleCommands;

import backendExceptions.BackendException;

public class AskWithCommand extends TurtleCommand {

    public AskWithCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected int getArgumentCount () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    protected double onExecute () throws BackendException {
        // TODO Auto-generated method stub
        return 0;
    }

}
