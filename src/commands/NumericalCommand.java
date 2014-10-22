package commands;

import commands.information.IVariableContainer;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;

public final class NumericalCommand extends BaseCommand{

    private double myNumber;
    
    public NumericalCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public double execute (SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
        return myNumber;
    }

    @Override
    protected void parseArguments (String userInput) {
        String number = userInput.split("\\s+")[0];
        myNumber = Double.parseDouble(number);
        String leftover = userInput.replaceFirst(number, "").trim();
        setLeftoverCommands(leftover);
    }
}
