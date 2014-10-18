package commands;

import communicator.IVariableContainer;
import backendExceptions.BackendException;
import View.Grid;
import turtle.Turtle;

public final class NumericalCommand extends BaseCommand{

    private double myNumber;
    
    public NumericalCommand(String userInput, double num) throws BackendException {
        this(userInput, true);
        myNumber = num;
    }
    
    private NumericalCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double execute (Grid grid, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
        return myNumber;
    }

    @Override
    protected void parseArguments (String userInput) {
        setLeftoverCommands(userInput);
    }
}
