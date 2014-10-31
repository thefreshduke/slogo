//this entire file is a part of my masterpiece
//Petra Ronald

package Buttons;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import View.GridTracker;

public abstract class ButtonFactory {
	protected Properties myPropertiesFile;
	
	public ButtonFactory(String myResources){
		myPropertiesFile=new Properties();
		this.loadInResources(myResources);
	}
	/**
	 * 
	 * @param buttonName		String representing the label of the Button
	 * @param myTracker			GridTracker for the GUIFunction
	 * @return					newly createdButton
	 */
	public abstract Button makeButton(String buttonName, GridTracker myTracker);
	private void loadInResources(String resourceFileName){
			InputStream stream = getClass().getClassLoader().getResourceAsStream("./resources/"+resourceFileName+".Properties");
			try {
				myPropertiesFile.load(stream);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Missing Resource File");
			}
	}
	public void changePropertiesFile(String resourceFileName){
		loadInResources(resourceFileName);
	}
	public Properties getPropertiesFile(){
		return myPropertiesFile;
	}
}
