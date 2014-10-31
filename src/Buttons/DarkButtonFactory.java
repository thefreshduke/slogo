//this entire file is a part of my Masterpiece
//Petra Ronald
package Buttons;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;
import javafx.scene.control.Button;
import GUIFunctions.GUIFunction;
import View.GridTracker;

public class DarkButtonFactory extends ButtonFactory{
	private final static String DEFAULT_STYLE_ERROR="Error in Button Style Class";
	private final static String DEFAULT_REFLECTION_ERROR="Error in Creating Button";
	public DarkButtonFactory(String myResources) {
		super(myResources);
	}

	public Button makeButton(String buttonName, GridTracker myGridTracker){
		Class<? extends Button> myButtonType;
		try {
			myButtonType = DarkButton.class;
			try {
				Class<? extends GUIFunction> myFunctionType=(Class<? extends GUIFunction>) Class.forName("GUIFunctions."+buttonName);
				GUIFunction myNewFunction=myFunctionType.getConstructor(GridTracker.class).newInstance(myGridTracker);
				String[] value=myPropertiesFile.getProperty(buttonName).split(";");
				DarkButton myNewButton=(DarkButton) myButtonType.getConstructor(String.class, double.class, double.class, GUIFunction.class)
						.newInstance(value[0], Double.parseDouble(value[1]), Double.parseDouble(value[2]), myNewFunction);		
				return myNewButton;
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					JOptionPane.showMessageDialog(null, DEFAULT_REFLECTION_ERROR);
				}
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, DEFAULT_STYLE_ERROR);
			}
			return null;
		}
	}