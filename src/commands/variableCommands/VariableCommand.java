package commands.variableCommands;

import turtle.Turtle;
import View.Grid;
import backendExceptions.BackendException;
import commands.BaseCommand;
import communicator.IVariableContainer;

public abstract class VariableCommand extends BaseCommand{

    private Grid myGrid;
    private Turtle myTurtle;
    private IVariableContainer myVariableContainer;
    
    public VariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute(Grid grid, Turtle turtle, IVariableContainer variableContainer) throws BackendException{
        myGrid = grid;
        myTurtle = turtle;
        myVariableContainer = variableContainer;
        double result = execute(variableContainer);
        if(getNextCommand() != null){
            return getNextCommand().execute(grid, turtle, variableContainer);
        }
        return result;
    }
    
    protected double executeCommand(BaseCommand command, IVariableContainer variableContainer) throws BackendException{
        return command.execute(myGrid, myTurtle, myVariableContainer);
    }
    
    protected IVariableContainer getVariableContainer(){
        return myVariableContainer;
    }
    
    protected abstract double execute(IVariableContainer variableContainer) throws BackendException;
}
