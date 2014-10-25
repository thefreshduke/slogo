package GUIFunctions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.swing.JOptionPane;

import View.MenuItemTemplate;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;

public class MyImages extends MenuBar {
	private ArrayList<MenuItemTemplate> myImages=new ArrayList<MenuItemTemplate>();
	private SetBackgroundImage backgroundFunction;
		public MyImages(GUIFunction myFunction){
			try {
				Properties prop = new Properties();
				InputStream stream = getClass().getClassLoader().getResourceAsStream("./resources/BackgroundImageFiles.Properties");
				prop.load(stream);
				for(Object image : prop.keySet()){
					Image newImage=new Image(prop.getProperty((String) image));
					this.makeBar(myFunction, (String) image, newImage);
				}
			}
			 catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Image File not Found");

			}
			backgroundFunction=(SetBackgroundImage) myFunction;
			
		}
		private void makeBar(GUIFunction myFunction, String myName, Image img){
			MenuItemTemplate myTemplate=new MenuItemTemplate(myName, event->backgroundFunction.gridSetAction(img));
			myImages.add(myTemplate);
		}
	public void getMyImage (int i){
		
	}
}
