package viewCommands;
import turtle.Turtle;

/**
 * 
 * 
 */
public abstract class ModelCommand extends BaseCommand {

	public ModelCommand(String userInput) {
		super(userInput);
	}

	protected abstract void parseArguments(String userInput);


	@Override
	public void setFlags() {
		setFrontEndFlag(false);
		setBackEndFlag(true);
	}
}