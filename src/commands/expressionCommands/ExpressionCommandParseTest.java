package commands.expressionCommands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import backendExceptions.BackendException;
import commandParser.CommandFactory;
import commands.BaseCommand;

public class ExpressionCommandParseTest {

	@Test
	public void testSumParses() {
		setFactory();
		String input = "SUM 6 SUM 7 8 SUM 8 9 SUM 9 10";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(19.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test 
	public void testDifference() {
		setFactory();
		String input = "DIFFERENCE 6.8 SUM 7 8";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(6.8 - (7 + 8)));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testProduct() {
		setFactory();
		double first = 6.8;
		double second = 15;
		String input = "PRODUCT " + first + " " + second;
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(first * second));
		}
		catch(BackendException ex) {

		}
	}

	public void testQuotient() {
		setFactory();
		double first = 6.8;
		double second = 5.3;
		String input = "QUOTIENT " + first + " " + second;
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(first/second));
		}
		catch(BackendException ex) {

		}
	}

	public void testRemainder() {
		setFactory();
		double first = 6.8; 
		double second = 2.3;
		String input = "REMAINDER " + first + " " + second;
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(first % second));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testRandom() {
		setFactory();
		int count = 50;
		while(count > 0){
			double max = Math.random()*count;
			String input = "RANDOM " + max;
			BaseCommand command = CommandFactory.createCommand(input, false);
			double result = 100;
			try {
				result = command.execute(null, null, null);
			}
			catch (BackendException e) {
			}
			assertTrue(result < max);
			count -= 1;
		}
	}

	@Test
	public void testMinus() {
		setFactory();
		double param = 264;
		String input = "MINUS " + param;
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.execute(null, null, null);
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(-param));
	}

	@Test
	public void testPower() {
		setFactory();
		double first = 6.8; 
		double second = 2.3;
		String input = "POW " + first + " " + second;
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(Math.pow(first, second)));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testSin() {
		setFactory();
		double angle = 264;
		String input = "SIN " + angle;
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.execute(null, null, null);
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.sin(Math.toRadians(angle))));
	}

	@Test
	public void testCos() {
		setFactory();
		double angle = 264;
		String input = "COS " + angle;
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.execute(null, null, null);
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.cos(Math.toRadians(angle))));
	}

	@Test
	public void testTan() {
		setFactory();
		double angle = 264;
		String input = "TAN " + angle;
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.execute(null, null, null);
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.tan(Math.toRadians(angle))));
	}

	@Test
	public void testATan() {
		setFactory();
		double angle = 264;
		String input = "ATAN " + angle;
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.execute(null, null, null);
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.atan(Math.toRadians(angle))));
	}

	@Test
	public void NaturalLogTest() {
		setFactory();
		double param = 264;
		String input = "LOG " + param;
		BaseCommand command = CommandFactory.createCommand(input, false);
		Double result = null;
		try {
			result = command.execute(null, null, null);
		}
		catch (BackendException ex) {

		}
		assertEquals(result, new Double(Math.log(param)));
	}

	@Test
	public void testLess() {
		setFactory();
		String input = "LESSP 2 10"; //LESS? doesn't work for some reason... it has something to do with the question mark
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testGreater() {
		setFactory();
		String input = "GREATERP 2 1";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testEqual() {
		setFactory();
		String input = "EQUALP 3 3";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testNotEqual() {
		setFactory();
		String input = "NOTEQUALP 3 4";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testAnd() {
		setFactory();
		String input = "AND 3 4";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testOr() {
		setFactory();
		String input = "OR 3 0";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	@Test
	public void testNot() {
		setFactory();
		String input = "NOT 0";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try {
			Double result = command.execute(null, null, null);
			assertEquals(result, new Double(1.0));
		}
		catch(BackendException ex) {

		}
	}

	private void setFactory() {
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

		commandToClassMap.put("LESSP", LessCommand.class);
		commandToClassMap.put("LESS?", LessCommand.class);
		commandToClassMap.put("GREATERP", GreaterCommand.class);
		commandToClassMap.put("GREATER?", GreaterCommand.class);
		commandToClassMap.put("EQUALP", EqualCommand.class);
		commandToClassMap.put("EQUAL?", EqualCommand.class);
		commandToClassMap.put("NOTEQUALP", NotEqualCommand.class);
		commandToClassMap.put("NOTEQUAL?", NotEqualCommand.class);
		commandToClassMap.put("AND", AndCommand.class);
		commandToClassMap.put("OR", OrCommand.class);
		commandToClassMap.put("NOT", NotCommand.class);
		CommandFactory.setCommandToClassRelation(commandToClassMap);
	}
}
