package View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import communicator.BaseController;

public class SlogoViewModel {
	private BaseController myBaseController;
	private Stage myStage;

	public SlogoViewModel(BaseController myController){
		myBaseController = myController;
	}



	public void loadLanguageResource(String language){
		String filePath = "/resources/languages/" + language + ".Properties"; 
		File file = new File(filePath);
		myBaseController.loadLanguage(file);
	}


	
	public void helpPage(){
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		//		webEngine.load("./resources/helpInfo/commands.PHP");
		webEngine.load("http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php");
		Stage helpStage = new Stage();
		Scene scene = new Scene(browser, 700, 480);
		helpStage.setScene(scene);
		helpStage.show();
	}


	


}
