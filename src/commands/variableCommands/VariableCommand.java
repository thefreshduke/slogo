package commands.variableCommands;

import model.CommandWrapper;
import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.BaseCommand;
import communicator.IVariableContainer;

public abstract class VariableCommand extends BaseCommand{

    private CommandWrapper myWrapper;
    
    public VariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute(CommandWrapper wrapper) throws BackendException{
    	myWrapper = wrapper;
        double result = execute(wrapper.getVariableContainer());
        if(getNextCommand() != null){
            return getNextCommand().execute(wrapper);
        }
        return result;
    }
    
    protected double executeCommand(BaseCommand command, IVariableContainer variableContainer) throws BackendException{
        return command.execute(myWrapper);
    }
    
    protected IVariableContainer getVariableContainer(){
        return myWrapper.getVariableContainer();
    }
    
    protected abstract double execute(IVariableContainer variableContainer) throws BackendException;
}
