package commands.viewQueries;

import turtle.Turtle;
import View.SlogoView;
import backendExceptions.BackendException;
import commands.ViewCommand;
import commands.information.IVariableContainer;

public class PenDownQuery extends ViewCommand {

	public PenDownQuery(String command, boolean isExpression) throws BackendException {
		super(command, isExpression);
	}

	@Override
	public void updateTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double execute(SlogoView view, Turtle turtle, IVariableContainer variableContainer) throws BackendException {
//		return penIsDown ? 1 : 0; //return 1 if pen is down, 0 if not --- check SlogoView.java in View package
		// make pen part of the turtle object for easy access? or change penIsDown boolean in SlogoView to public?
		System.out.println("Is pen down?");
		return 0;
	}

	@Override
	protected void parseArguments(String userInput) throws BackendException {
		
	}
	
}
