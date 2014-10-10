package commands;

import View.SlogoView;
import turtle.Turtle;

public class NumericalCommand extends BaseCommand{

    private int myNumber;
    
    public NumericalCommand(String userInput, Integer num){
        this(userInput, true);
        myNumber = num;
    }
    
    public NumericalCommand (String userInput, boolean isExpression) {
        super(userInput, isExpression);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double execute (SlogoView view, Turtle turtle) {
        return myNumber;
    }

    @Override
    protected void parseArguments (String userInput) {
        setLeftoverCommands(userInput);
    }
}
