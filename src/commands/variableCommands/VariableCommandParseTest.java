package commands.variableCommands;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.CommandWrapper;

import org.junit.Test;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;
import commands.BaseCommand;
import commands.expressionCommands.ArcTanCommand;
import commands.expressionCommands.CosCommand;
import commands.expressionCommands.DifferenceCommand;
import commands.expressionCommands.ExponentCommand;
import commands.expressionCommands.NaturalLogCommand;
import commands.expressionCommands.NegativeCommand;
import commands.expressionCommands.ProductCommand;
import commands.expressionCommands.QuotientCommand;
import commands.expressionCommands.RandomIntegerCommand;
import commands.expressionCommands.RemainderCommand;
import commands.expressionCommands.SinCommand;
import commands.expressionCommands.SumCommand;
import commands.expressionCommands.TanCommand;
import commands.information.IVariableContainer;
import commands.information.MapBasedVariableContainer;
import static org.junit.Assert.assertEquals;

public class VariableCommandParseTest {

<<<<<<< HEAD
	//    @Test
	//    public void testThatVariableIsAdded() {
	//        setFactory();
	//        IVariableContainer container = new MapBasedVariableContainer();
	//        String variable1 = "variable1";
	//        String value = " SUM 10 60";
	//        String input = "MAKE " + variable1 + value;
	//        BaseCommand variableSetCommand = CommandFactory.createCommand(input, false);
	//        try {
	//            variableSetCommand.execute(null, null, container);
	//            BaseCommand command = container.getValue(variable1);
	//            Double result = command.execute(null, null, container);
	//            assertEquals(result, new Double(70));
	//            
	//            Double nonExistentResult = container.getValue("fff").execute(null, null, container);
	//            assertEquals(nonExistentResult, new Double(0.0));
	//        }
	//        catch (Exception ex){
	//            
	//        }
	//    }

	@Test
	public void testThatVariableIsGet() throws BackendException {
		IVariableContainer container = new MapBasedVariableContainer();
		String variable1 = "varx";
		String valueString = "SUM 10 60";
		valueString = processInput(valueString);
		BaseCommand value = CommandFactory.createCommand(valueString, true);
		BaseCommand command = CommandFactory.createCommand(processInput(":" + variable1), true);
		try{
			container.addVariable(variable1, value);
			Double result = command.execute(null,  null, container);
			assertEquals(result, new Double(70));
		}
		catch (Exception ex){

		}
	}

	private String processInput(String input) throws BackendException {		
		LanguageFileParser myTranslator = new LanguageFileParser(new File("src/resources/languages/English.properties"));
		CommandToClassTranslator commandToClassTranslator = new CommandToClassTranslator();
		try {
			CommandFactory
			.setCommandToClassRelation(commandToClassTranslator
					.translateCommandToClass(new File("src/resources/languages/EnglishToClassName.properties")));
		}
		catch (BackendException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return   myTranslator.translateUserInputIntoEnglish(input);
	}
=======
//    @Test
//    public void testThatVariableIsAdded() {
//        setFactory();
//        IVariableContainer container = new MapBasedVariableContainer();
//        String variable1 = "variable1";
//        String value = " SUM 10 60";
//        String input = "MAKE " + variable1 + value;
//        BaseCommand variableSetCommand = CommandFactory.createCommand(input, false);
//        try {
//            variableSetCommand.execute(null, null, container);
//            BaseCommand command = container.getValue(variable1);
//            Double result = command.execute(null, null, container);
//            assertEquals(result, new Double(70));
//            
//            Double nonExistentResult = container.getValue("fff").execute(null, null, container);
//            assertEquals(nonExistentResult, new Double(0.0));
//        }
//        catch (Exception ex){
//            
//        }
//    }
    
    @Test
    public void testThatVariableIsGet() {
    	setFactory();
    	IVariableContainer container = new MapBasedVariableContainer();
        String variable1 = "variable1";
        String valueString = "SUM 10 60";
        BaseCommand value = CommandFactory.createCommand(valueString, true);
        String commandString = ":" + variable1;
        BaseCommand command = CommandFactory.createCommand(commandString, true);
        try{
        	container.addVariable(":"+ variable1, value);
        	CommandWrapper wrapper = new CommandWrapper();
        	wrapper.setVariableContainer(container);
        	Double result = command.execute(wrapper);
        	assertEquals(result, new Double(70));
        }
        catch (Exception ex){
        	
        }
    }
    
    private void setFactory(){
        Map<String, Class> commandToClassMap = new HashMap<>();
        commandToClassMap.put("SUM", SumCommand.class);
        commandToClassMap.put("DIFFERENCE", DifferenceCommand.class);
        commandToClassMap.put("PRODUCT", ProductCommand.class);
        commandToClassMap.put("QUOTIENT", QuotientCommand.class);
        commandToClassMap.put("REMAINDER", RemainderCommand.class);
        commandToClassMap.put("RANDOM", RandomIntegerCommand.class);
        commandToClassMap.put("POW", ExponentCommand.class);
        
        commandToClassMap.put("MINUS", NegativeCommand.class);     
        commandToClassMap.put("SIN", SinCommand.class);
        commandToClassMap.put("COS", CosCommand.class);
        commandToClassMap.put("TAN", TanCommand.class);
        commandToClassMap.put("ATAN", ArcTanCommand.class);
        commandToClassMap.put("LOG", NaturalLogCommand.class);
        
        commandToClassMap.put("MAKE", SetVariableCommand.class);
        CommandFactory.setCommandToClassRelation(commandToClassMap);
    }
>>>>>>> b68372fa2d93ef21b91af967c286ca714bf2bcf3
}