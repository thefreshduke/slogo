package commands;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void testSumParses(){
        String input = "SUM 6 SUM 7 8 SUM 8 9 SUM 9 10";
        BaseCommand command = TestFactory.createCommand(input, false);
        double x = command.execute(null, null);
        assertEquals(new Double(command.execute(null, null)), new Double(19.0));

    }
    
    @Test
    public void testIfElse(){
        String input = "IFELSE SUM 6 7 [ SUM 7 7 ] [ SUM 2 3 ]";
        BaseCommand command = TestFactory.createCommand(input, true);
        double result = command.execute(null, null);
        assertEquals(new Double(result), new Double(14));
    }
}
