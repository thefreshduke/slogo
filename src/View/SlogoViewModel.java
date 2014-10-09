package View;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import communicator.BaseController;

public class SlogoViewModel {
	private BaseController myBaseController;
	private SlogoView mySlogoView;
	private Stage myStage;
	
	public SlogoViewModel(BaseController myController, SlogoView myView){
		myBaseController = myController;
		mySlogoView = myView;
	}
	
	

	public void loadLanguageResource(String language){
		String filePath = "/resources/languages/" + language + ".Properties"; 
//		myBaseController.loadLanguage(url);
		System.out.println(filePath);
	}
	
	
	public void penDown(){
		mySlogoView.setPenDown(true);
	}
	
	public void penUp(){
		mySlogoView.setPenDown(false);
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
	
	
	public void uploadTurtleImage(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Turtle Image");
		fileChooser.setInitialDirectory(new File("./"));
		File file = fileChooser.showOpenDialog(myStage);
		if(file != null){
			String url = file.getPath();
			
		}
	}
	
	

	
}
