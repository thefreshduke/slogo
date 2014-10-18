package View;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import turtle.Turtle;

public class TurtleImageChange implements GUIFunction {
	private Collection<Turtle> myTurtles;
	private Stage myStage;
	public TurtleImageChange(Collection<Turtle> myCurrentTurtles, Stage main){
		myTurtles=myCurrentTurtles;
	}
	@Override
	public void doAction() {
		
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select Turtle Image");
			fileChooser.setInitialDirectory(new File("./"));
			File file = fileChooser.showOpenDialog(myStage);
			if(file != null&&(file.getName().contains(".JPG")||file.getName().contains(".png"))){
				BufferedImage buffer;
				try {
					buffer = ImageIO.read(file);
					Image img=SwingFXUtils.toFXImage(buffer, null);
					for (Turtle t: myTurtles){
						t.setImage(img);
					}
				} 
				catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Please select another file");
				}
			}
				else
					JOptionPane.showMessageDialog(null, "Please select another file");
	}	
}
