package commandParser;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import backendExceptions.BackendException;
import commands.BaseCommand;
import commands.information.BaseVariableContainer;
import commands.information.ICommandInformationHub;
import commands.information.IInformationContainer;

/**
 * @author Rahul Harikrishnan, Duke Kim, $cotty $haw
 *
 * Our Command Factory uses reflection to hide implementation details and
 * creates the commands from the user's inputs using a key-value system.
 * 
 * It is static because our createCommand method is recursive, so we want
 * to avoid passing the factory to the individual commands.
 */
public class CommandFactory {

    private static final String INVALID_COMMAND_CLASS_TYPE_MESSAGE = "Invalid command class type";
    @SuppressWarnings("rawtypes")
    private static Map<String, Class> myCommandToClassMap;
    private static String myCommandSeparator = "\\s+";
    private static ICommandInformationHub myInformationHub;
//    @SuppressWarnings({ "rawtypes", "unused" })
//    private static Class[] x = { BaseVariableContainer.class };

    @SuppressWarnings("unchecked")
    public static BaseCommand createCommand (String input, boolean isExpression)
            throws BackendException {
        if (input == null || input.equals("") || input.trim().equals("")) {
            return null;
        }
        String trimmedInput = input.trim();
        String firstCommand = identifyFirstCommand(trimmedInput);
        String subInput = input.replaceFirst(firstCommand, "").trim();
        Class<BaseCommand> commandClass = myCommandToClassMap.get(firstCommand);
        BaseCommand command = null;
        if (commandClass == null) {
            BaseVariableContainer variableContainer = (BaseVariableContainer)myInformationHub
                    .getContainer(BaseVariableContainer.class);
            command = variableContainer.getCreatedCommand(firstCommand, subInput, isExpression);
        }
        else {
            try {
                command = commandClass.getConstructor(String.class, boolean.class).newInstance(
                        subInput, isExpression);
            }
            catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new BackendException(e, INVALID_COMMAND_CLASS_TYPE_MESSAGE);
            }
        }
        Set<Class<? extends IInformationContainer>> containerTypes = command
                .getRequiredInformationTypes();
        if (containerTypes != null) {
            Collection<IInformationContainer> containers = myInformationHub
                    .getContainers(containerTypes);
            command.setRequiredInformation(containers);
        }
        return command;
    }

    public static void setInformationHub (ICommandInformationHub informationHub) {
        myInformationHub = informationHub;
    }

    @SuppressWarnings("rawtypes")
    public static void setCommandToClassRelation (Map<String, Class> commandToClassMap) {
        myCommandToClassMap = commandToClassMap;
    }

    private static String identifyFirstCommand (String input) {
        String[] commandList = input.split(myCommandSeparator);
        String firstCommand = commandList[0];
        return firstCommand;
    }
}