package commands;
import turtle.Turtle;

/**
 * View command that extends BaseCommand, and whose sole purpose is to update 
 * the view.
 */

public abstract class ViewCommand extends BaseCommand {

	public ViewCommand(String command, boolean isExpression) {
		super(command, isExpression);
	}

	public abstract void updateTurtle(Turtle turtle);

}