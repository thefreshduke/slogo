package GUIFunctions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import View.GridTracker;
import View.SingleGrid;
import turtle.Turtle;

public class SetBackgroundImage extends PersonalizeMenu {
	private Stage myStage;
	private ImageView myImageView;
	public SetBackgroundImage(GridTracker grid, Stage mainStage){
		allGrids=grid;
		myStage=mainStage;
		myImageView=new ImageView();

	}
	@Override
	public void doAction() {
			allGrids.getActiveGrid().getChildren().add(myImageView);
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select Background Image");
			fileChooser.setInitialDirectory(new File("./"));
			File file = fileChooser.showOpenDialog(myStage);
			if(file != null&&(file.getName().toUpperCase().contains(".JPG")||file.getName().toUpperCase().contains(".PNG")||file.getName().toUpperCase().contains(".JPEG"))){
			BufferedImage buffer;
			try {
				buffer = ImageIO.read(file);
				Image img=SwingFXUtils.toFXImage(buffer, null);
				this.gridSetAction(img);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Please select another file");
			}


		}
	
	}
	public void gridSetAction(Image img){
		myImageView.setImage(img);
		myImageView.setFitHeight(allGrids.getActiveGrid().getMyHeight());
		myImageView.setFitWidth(allGrids.getActiveGrid().getMyWidth());
		myImageView.setVisible(true);
		allGrids.getActiveGrid().getChildren().removeAll(allGrids.getActiveGrid().getAllTurtles());
		allGrids.getActiveGrid().getChildren().addAll(allGrids.getActiveGrid().getAllTurtles());
		allGrids.getActiveGrid().getChildren().removeAll(allGrids.getActiveGrid().getAllPens());
		allGrids.getActiveGrid().getChildren().addAll(allGrids.getActiveGrid().getAllPens());
	}
	@Override
	public void doAction(List<? extends Number> newVal) {
		// TODO Auto-generated method stub
		
	}
}
