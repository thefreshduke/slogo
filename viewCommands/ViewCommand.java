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


	@Override
	public void updateTurtle(Turtle turtle) {

	}

	@Override
	public double executeCommand() {
		return 0;
	}
}