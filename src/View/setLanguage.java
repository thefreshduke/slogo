package View;

import java.io.File;

import GUIFunctions.GUIFunction;
import communicator.MainController;

public class setLanguage implements GUIFunction {

	private MainController myController;
	private File languageFile;
	
	public setLanguage(MainController c, String filePath){
	
		myController = c;
		languageFile = new File(filePath);
		
	}
	
	
	@Override
	public void doAction() {
		
		myController.loadLanguage(languageFile);
		
	}

}
