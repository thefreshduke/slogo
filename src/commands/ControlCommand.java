package commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import commands.information.BaseTurtleContainer;
import commands.information.BaseVariableContainer;
import commands.information.IInformationContainer;
import commands.information.IVariableContainer;
import backendExceptions.BackendException;
import View.SlogoView;
import turtle.Turtle;


public abstract class ControlCommand extends ModelCommand {

    protected static String COMMAND_INDICATOR = "liststart";
    protected static String COMMAND_END_INDICATOR = "listend";
    protected static String COMMAND_SEPARATOR = " ";
    protected static String VARIABLE_INDICATOR = "variable";
    private SlogoView myView;
    private Turtle myTurtle;
    private IVariableContainer myVariableContainer;
    
    public ControlCommand (String userInput, boolean isExpression) throws BackendException {
        super(userInput, isExpression);
    }

    @Override
    public final double execute (SlogoView view, Turtle turtle, IVariableContainer variableContainer)
                                                                                                     throws BackendException {
        myView = view;
        myTurtle = turtle;
        myVariableContainer = variableContainer;
        return execute(variableContainer);
    }

    protected double executeCommand (BaseCommand command, IVariableContainer variableContainer)
                                                                                               throws BackendException {
        return command.execute(myView, myTurtle, myVariableContainer);
    }

    @Override
	public Set<Class<? extends IInformationContainer>> getRequiredInformationTypes(){
		Set<Class<? extends IInformationContainer>> typeSet = new HashSet<>();
		typeSet.add(BaseVariableContainer.class);
		return typeSet;
	}
	
	public void setRequiredInformation(Collection<IInformationContainer> containers){
		if(containers.size() != 1){
			//throw throw new BAckendException
		}
		ArrayList<IInformationContainer> containerList = new ArrayList<>(containers);
		IInformationContainer container = containerList.get(0);
		boolean extendsVariableContainer = IVariableContainer.class.isAssignableFrom(container.getClass());
		if(!extendsVariableContainer) {
			//throw exception
		}
		IVariableContainer variableContainer = (IVariableContainer)container;
		myVariableContainer = variableContainer;
	}
	
	protected IVariableContainer getVariableContainer(){
		return myVariableContainer;
	}
	
    public abstract double execute (IVariableContainer variableContainer) throws BackendException;

    protected String[] splitByInnerListCommand (String input) {
        String treatedInput = input.trim() + COMMAND_SEPARATOR;
        if (!startsWithCommandStartIndicator(treatedInput)) {
            // exception
        }
        int endIndex = findIndexOfInnerListCommandEnd(treatedInput);
        if (endIndex == -1) {
            // exception
        }
        String innerListCommand =
                treatedInput.substring(COMMAND_INDICATOR.length(),
                                       endIndex - COMMAND_END_INDICATOR.length()).trim();
        String outsideString = treatedInput.substring(endIndex).trim();
        String[] splitCommand = { innerListCommand, outsideString};
        return splitCommand;
    }

    private int findIndexOfInnerListCommandEnd (String input) {
        Stack<String> checkStack = new Stack<>();
        StringBuilder temporaryStringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            Character character = input.charAt(i);
            temporaryStringBuilder.append(character);
            if (COMMAND_SEPARATOR.equals(character.toString())) {
                String aggregatedWord = temporaryStringBuilder.toString().trim();
                if (aggregatedWord.equals(COMMAND_INDICATOR)) {
                    checkStack.push(aggregatedWord);
                }
                else if (aggregatedWord.equals(COMMAND_END_INDICATOR)) {
                    checkStack.pop();
                }
                if (checkStack.size() == 0) { 
                    return i; 
                }
                temporaryStringBuilder.setLength(0);
            }
        }
        return -1;
    }

    protected boolean startsWithCommandStartIndicator (String input) {
        String splitInput = input.trim().split(COMMAND_SEPARATOR, 2)[0];
        return splitInput.equals(COMMAND_INDICATOR);
    }
}
