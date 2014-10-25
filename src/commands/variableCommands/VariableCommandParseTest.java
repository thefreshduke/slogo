package commands.variableCommands;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import backendExceptions.BackendException;

import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;
import commands.BaseCommand;
import commands.information.BaseVariableContainer;
import commands.information.MapBasedVariableContainer;

public class VariableCommandParseTest {

    // @Test
    // public void testThatVariableIsAdded() {
    // setFactory();
    // IVariableContainer container = new MapBasedVariableContainer();
    // String variable1 = "variable1";
    // String value = " SUM 10 60";
    // String input = "MAKE " + variable1 + value;
    // BaseCommand variableSetCommand = CommandFactory.createCommand(input,
    // false);
    // try {
    // variableSetCommand.execute(null, null, container);
    // BaseCommand command = container.getValue(variable1);
    // Double result = command.execute(null, null, container);
    // assertEquals(result, new Double(70));
    //
    // Double nonExistentResult = container.getValue("fff").execute(null, null,
    // container);
    // assertEquals(nonExistentResult, new Double(0.0));
    // }
    // catch (Exception ex){
    //
    // }
    // }

    @Test
    public void testThatVariableIsGet () throws BackendException {
        BaseVariableContainer container = new MapBasedVariableContainer();
        String variable1 = "varx";
        String valueString = "SUM 10 60";
        valueString = processInput(valueString);
        BaseCommand value = CommandFactory.createCommand(valueString, true);
        BaseCommand command = CommandFactory.createCommand(processInput(":" + variable1), true);
        try {
            container.addVariable(variable1, value);
            Double result = command.execute();
            assertEquals(result, new Double(70));
        } catch (Exception ex) {

        }
    }

    private String processInput (String input) throws BackendException {
        LanguageFileParser myTranslator = new LanguageFileParser(new File(
                "src/resources/languages/English.properties"));
        CommandToClassTranslator commandToClassTranslator = new CommandToClassTranslator();
        try {
            CommandFactory.setCommandToClassRelation(commandToClassTranslator
                    .translateCommandToClass(new File(
                            "src/resources/languages/EnglishToClassName.properties")));
        } catch (BackendException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return myTranslator.translateUserInputIntoEnglish(input);
    }
}