package Buttons;

import static org.junit.Assert.*;
import java.util.ArrayList;
import javafx.scene.control.Button;
import org.junit.Test;
import View.GridTracker;


public class ButtonFactoryTest {
	public ButtonFactoryTest(){		
	}
	
	@Test
	public void testButtonChooser(){
		ButtonFactoryChooser myButtonStyle=new ButtonFactoryChooser();
		ButtonFactory myButtonFactory=myButtonStyle.getButtonFactory("Buttons.DarkButtonFactory", "MyButtonBottomPanel");
		assertSame("ButtonFactory: DarkButtonFactory", myButtonFactory.getClass(), DarkButtonFactory.class);
	}
	@Test
	
	public void testProperties(){
		ButtonFactoryChooser myButtonChooser=new ButtonFactoryChooser();
		ButtonFactory darkButtonFactory=myButtonChooser.getButtonFactory("Buttons.DarkButtonFactory", "MyButtonBottomPanel");
		for (Object key: darkButtonFactory.getPropertiesFile().keySet()){
			String[] myValues=darkButtonFactory.getPropertiesFile().getProperty((String) key).split(";");
			Class myFunction;
			try {
				myFunction = Class.forName("GUIFunctions."+(String) key);
				assertNotNull(myFunction);
			} catch (ClassNotFoundException e) {
				assertEquals(1, 0, 0);
			}
		}
	}	
}
