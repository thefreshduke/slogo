package commands.expressionCommands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commandParser.CommandToClassTranslator;
import commandParser.LanguageFileParser;
import commands.BaseCommand;

public class ExpressionCommandParseTest {

	@Test
	public void testSumParses() throws BackendException {
		String input = "SUM 6 SUM 7 8 SUM 8 9 SUM 9 10";
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(19.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test 
	public void testDifference() throws BackendException {
		String input = "DIFFERENCE 6.8 SUM 7 8";
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(6.8 - (7 + 8)));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testProduct() throws BackendException {
		double first = 6.8;
		double second = 15;
		String input = "PRODUCT " + first + " " + second;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(first * second));
		}
		catch(BackendException ex) {

		}
	}

	public void testQuotient() throws BackendException {
		double first = 6.8;
		double second = 5.3;
		String input = "QUOTIENT " + first + " " + second;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(first/second));
		}
		catch(BackendException ex) {

		}
	}

	public void testRemainder() throws BackendException {
		double first = 6.8; 
		double second = 2.3;
		String input = "REMAINDER " + first + " " + second;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(first % second));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testRandom() throws BackendException {
		int count = 50;
		while(count > 0){
			double max = Math.random()*count;
			String input = "RANDOM " + max;
			input = processInput(input);
			BaseCommand command = CommandFactory.createCommand(input, false);
			double result = 100;
			try {
				result = command.onExecute();
			}
			catch (BackendException e) {
			}
			assertTrue(result < max);
			count -= 1;
		}
	}

	@Test
	public void testMinus() throws BackendException {
		double param = 264;
		String input = "MINUS " + param;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.onExecute();
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(-param));
	}

	@Test
	public void testPower() throws BackendException {
		double first = 6.8; 
		double second = 2.3;
		String input = "POW " + first + " " + second;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(Math.pow(first, second)));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testSin() throws BackendException {
		double angle = 264;
		String input = "SIN " + angle;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.onExecute();
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.sin(Math.toRadians(angle))));
	}

	@Test
	public void testCos() throws BackendException {
		double angle = 264;
		String input = "COS " + angle;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.onExecute();
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.cos(Math.toRadians(angle))));
	}

	@Test
	public void testTan() throws BackendException {
		double angle = 264;
		String input = "TAN " + angle;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.onExecute();
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.tan(Math.toRadians(angle))));
	}

	@Test
	public void testATan() throws BackendException {
		double angle = 264;
		String input = "ATAN " + angle;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.onExecute();
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.atan(Math.toRadians(angle))));
	}

	@Test
	public void NaturalLogTest() throws BackendException {
		double param = 264;
		String input = "LOG " + param;
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.onExecute();
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.log(param)));
	}

	@Test
	public void testLess() throws BackendException {
		String input = "LESSP 2 10"; //LESS? doesn't work for some reason... it has something to do with the question mark
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testGreater() throws BackendException {
		String input = "GREATERP 2 1";
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testEqual() throws BackendException {
		String input = "EQUALP 3 3";
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testNotEqual() throws BackendException {
		String input = "NOTEQUALP 3 4";
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testAnd() throws BackendException {
		String input = "AND 3 4";
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testOr() throws BackendException {
		String input = "OR 3 0";
		input = processInput(input);
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testNot() throws BackendException {
		String input = "sum 5 5";
		input = processInput(input);

		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.onExecute();
			assertEquals(result, new Double(10.0));
		}
		catch(BackendException ex) {

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
}
