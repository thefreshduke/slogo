package commands;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import backendExceptions.BackendException;
import commandParser.CommandFactory;

public class SimpleTest {

	private Map<String, Class> myCommandToClassMap;

	@Test
	public void testSumParses(){
		myCommandToClassMap = new HashMap<>();
		myCommandToClassMap.put("SUM", SumCommand.class);
		myCommandToClassMap.put("IFELSE", IfElseCommand.class);
		myCommandToClassMap.put("FD", ForwardCommand.class);
		CommandFactory.setCommandToClassRelation(myCommandToClassMap);
		String input = "SUM 6 SUM 7 8 SUM 8 9 SUM 9 10";
		BaseCommand command = CommandFactory.createCommand(input, false);
		try{
			Double result = command.execute(null, null);
			assertEquals(result, new Double(19.0));
		}
		catch(BackendException ex){ 
		}
	}

	//    @Test
	//    public void testIfElse(){
	//        String input = "IFELSE SUM 6 7 [ SUM 7 7 ] [ SUM 2 3 ]";
	//        BaseCommand command = CommandFactory.createCommand(input, true);
	//        double result = command.execute(null, null);
	//        assertEquals(new Double(result), new Double(14));
	//    }
}
