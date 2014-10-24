package commands;

import java.util.Collection;
import java.util.Set;

import commands.information.IInformationContainer;
import backendExceptions.BackendException;

public final class NumericalCommand extends BaseCommand{

    private double myNumber;
    
    public NumericalCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    protected double onExecute () throws BackendException {
        return myNumber;
    }

    @Override
    protected void parseArguments (String userInput) {
        String number = userInput.split("\\s+")[0];
        myNumber = Double.parseDouble(number);
        String leftover = userInput.replaceFirst(number, "").trim();
        setLeftoverCommands(leftover);
    }

	@Override
	public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes() {
		return null;
	}

	@Override
	public void setRequiredInformation(
			Collection<IInformationContainer> containers) {}
}
