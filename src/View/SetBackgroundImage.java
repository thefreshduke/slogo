package View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import turtle.Turtle;

public class SetBackgroundImage implements GUIFunction {
	private Grid myGrid;
	private Stage myStage;
	private ImageView myImageView;
	public SetBackgroundImage(Grid grid, Stage mainStage){
		myGrid=grid;
		myStage=mainStage;
		myImageView=new ImageView();
		myGrid.getChildren().add(myImageView);
	}
	@Override
	//ugly but works need to change
	public void doAction() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select Background Image");
			fileChooser.setInitialDirectory(new File("./"));
			File file = fileChooser.showOpenDialog(myStage);
			if(file != null&&(file.getName().contains(".JPG")||file.getName().contains(".png"))){
			
			BufferedImage buffer;
			try {
				buffer = ImageIO.read(file);
				Image img=SwingFXUtils.toFXImage(buffer, null);
				myImageView.setImage(img);
				myImageView.setFitHeight(myGrid.myHeight);
				myImageView.setFitWidth(myGrid.myWidth);
				myImageView.setVisible(true);
				myGrid.getChildren().removeAll(myGrid.getAllTurtles());
				myGrid.getChildren().addAll(myGrid.getAllTurtles());
				myGrid.getChildren().removeAll(myGrid.getAllPens());
				myGrid.getChildren().addAll(myGrid.getAllPens());
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Please select another file");
			}


		}
	
	}
}
