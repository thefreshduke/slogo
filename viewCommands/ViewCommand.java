package viewCommands;
import turtle.Turtle;

/**
 * View command that extends BaseCommand, and whose sole purpose is to update 
 * the view.
 */

public abstract class ViewCommand extends BaseCommand {


	public ViewCommand(String command) {
		super(command);

	}

	public abstract void updateTurtle(Turtle turtle);


	@Override
	public double execute() {
		return 0;
	}
}