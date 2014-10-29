package GUIFunctions;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import View.SingleGrid;
import communicator.MainController;

public class SetLanguage extends LanguageMenu {
	
	@Override
	public File doAction(String s) {
		File file = new File("src/resources/languages/"+s+".properties");
		return file;
		
		
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}

	

}
