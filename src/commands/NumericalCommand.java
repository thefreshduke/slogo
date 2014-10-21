package commands;

import communicator.IVariableContainer;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public final class NumericalCommand extends BaseCommand{

    private double myNumber;
    
    public NumericalCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
        myNumber = Double.parseDouble(userInput);
    }

    @Override
    public double execute (SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
        return myNumber;
    }

    @Override
    protected void parseArguments (String userInput) {
        String number = userInput.split("\\s+")[0];
        String leftover = userInput.replaceFirst(number, "").trim();
        setLeftoverCommands(leftover);
    }
}
