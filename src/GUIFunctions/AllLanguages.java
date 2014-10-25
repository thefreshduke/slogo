package GUIFunctions;


import java.io.File;
import java.lang.reflect.InvocationTargetException;

import View.SingleGrid;

public class AllLanguages{
	String myName;
	
	public AllLanguages(String name){
		myName=name;
	}
	public File getLanguageFile() throws ClassNotFoundException{
	
		Language myLanguage=null;
		Class<Language> myClass=(Class<Language>) Class.forName(myName);
		try {
			myLanguage=myClass.getConstructor(null).newInstance();
			File languageFile=new File("/resources/languages" + myLanguage.getLanguageName() + ".properties");
			return languageFile;
		
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
