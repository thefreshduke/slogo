
/**
 * Abstract class for a Turtle command.  The update method will be implemented and
 * returns the turtle object that can be manipulated by other commands. All other 
 * types of commands will extend the BaseCommand, such as commands that only 
 * modify the view, commands that are conditional, mathematical operations. 
 *
 */
public abstract class BaseCommand {
	
	/**
	 * BaseCommand that takes a String representation of the command to be used 
	 * for parsing. 
	 * @param command - string representation of the command
	 */
	public BaseCommand(String command) {

	}
	/**
	 * Method takes in a the turtle and updates the turtle
	 * @param turtle - object that needs to be updated
	 * @return turtle - turtle resulting from update
	 */
	public abstract Turtle update(Turtle turtle);


}
