package commands;

import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public final class NumericalCommand extends BaseCommand{

    private double myNumber;
    
    public NumericalCommand(String userInput, double num){
        this(userInput, true);
        myNumber = num;
    }
    
    public NumericalCommand (String userInput, boolean isExpression) {
        super(userInput, isExpression);
    }

    @Override
    public double execute (SlogoView view, Turtle turtle) throws BackendException {
        return myNumber;
    }

    @Override
    protected void parseArguments (String userInput) {
        setLeftoverCommands(userInput);
    }
}
