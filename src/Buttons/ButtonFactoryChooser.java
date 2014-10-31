//this entire file is a part of my masterpiece
//Petra Ronald

package Buttons;

import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

public class ButtonFactoryChooser {
	private static final String DEFAULT_LOADING_TROUBLE_ERROR="Trouble loading Button Style. Loading Default Style";
	public ButtonFactoryChooser(){}
	/**
	 * 
	 * @param typeOfButtonFactory			String representing the Style of the Button
	 * @param resourceLocation				String representing the Properties file to read from
	 * @return
	 */
	public ButtonFactory getButtonFactory(String typeOfButtonFactory, String resourceLocation){
		try {
			Class myButtonFactoryType=Class.forName(typeOfButtonFactory);
			try {
				ButtonFactory myButtonFactory=(ButtonFactory) myButtonFactoryType.getConstructor(String.class).newInstance(resourceLocation);
				return myButtonFactory;
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				JOptionPane.showMessageDialog(null, "DEFAULT_LOADING_TROUBLE_ERROR");
				return null;
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "DEFAULT_LOADING_TROUBLE_ERROR");
			return null;
		}
	}
}
