package GUIFunctions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class AskForInitialFile implements GUIFunction {
	Stage myStage;
	public AskForInitialFile(){
		myStage=new Stage();
		Group myGroup=new Group();
		Scene myScene=new Scene(new Group());
		myStage.setHeight(1);
		myStage.setWidth(1);
		myStage.setScene(myScene);
		
		
	}
	public File sendFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File to Load");
		fileChooser.setInitialDirectory(new File("./"));
		File file = fileChooser.showOpenDialog(myStage);
		if(file != null){
			return file;
		}
		return null;
	}

	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		
	}
}
