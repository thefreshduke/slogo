package commands.controlCommands;

import commandParser.CommandFactory;
import commands.BaseCommand;
import commands.ControlCommand;
import backendExceptions.BackendException;

public class IfElseCommand extends ControlCommand {

	private BaseCommand myIfCommand;
	private BaseCommand myElseCommand;
	private BaseCommand myExpression;

	public IfElseCommand (String userInput, boolean isExpression) throws BackendException {
		super(userInput, isExpression);
	}

	@Override
	protected double onExecute () throws BackendException {
		double returnValue;
		if (myExpression.execute() != 0) {
			returnValue = myIfCommand.execute();
		}
		else {
			returnValue = myElseCommand.execute();
		}
		return returnValue;
	}

	@Override
	protected void parseArguments (String userInput) throws BackendException {
		myExpression = CommandFactory.createCommand(userInput, true);
		String innerCommandsInput = myExpression.getLeftoverString().trim();

		String[] firstSplitCommand = splitByInnerListCommand(innerCommandsInput);
		String ifCommandInput = firstSplitCommand[0];

		myIfCommand = CommandFactory.createCommand(ifCommandInput, false);

		String[] secondSplitCommand = splitByInnerListCommand(firstSplitCommand[1]);
		String elseCommandInput = secondSplitCommand[0];
		myElseCommand = CommandFactory.createCommand(elseCommandInput, false);

		String leftOverString = secondSplitCommand[1];
		setLeftoverCommands(leftOverString);
	}
}
