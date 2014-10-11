package commands.variableCommands;

import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;
import commands.BaseCommand;
import communicator.IVariableContainer;

public abstract class VariableCommand extends BaseCommand{

    private SlogoView myView;
    private Turtle myTurtle;
    private IVariableContainer myVariableContainer;
    
    public VariableCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException{
        myView = view;
        myTurtle = turtle;
        myVariableContainer = variableContainer;
        double result = execute(variableContainer);
        if(getNextCommand() != null){
            return getNextCommand().execute(view, turtle, variableContainer);
        }
        return result;
    }
    
    protected double executeCommand(BaseCommand command) throws BackendException{
        return command.execute(myView, myTurtle, myVariableContainer);
    }
    
    protected IVariableContainer getVariableContainer(){
        return myVariableContainer;
    }
    
    protected abstract double execute(IVariableContainer variableContainer) throws BackendException;
}
