package View;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import turtle.Turtle;
import communicator.BaseController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Grid extends Pane {
	private static final String REFERENCE_GRID_COLOR = "GREY";
	private String backgroundColor = "WHITE";
	public int myHeight;
	public int myWidth;
	private int translate=50;

	private ImageView myImageView;
	private Turtle myTurtle;
	private Stack<Line> myLines=new Stack<Line>();
	private HashSet<Line> myGridLines=new HashSet<Line>();
	
	
	public Grid(int height, int width, KeyFrame frame, Turtle turtle){//Turtle turtle){
		this.setPrefSize(width,height);
		myHeight=height/translate;
		myHeight=myHeight*translate;
		myWidth=width/translate;
		myWidth=myWidth*translate;
		Timeline time=new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(frame);
		makeGridLines();
		myTurtle=turtle;
		moveTurtle(myWidth/2, myHeight/2);
		getChildren().add(myTurtle);
		setBackgroundColor(backgroundColor);
	}

	public void setBackgroundColor(String color){
		backgroundColor = color;
		this.getChildren().remove(myImageView);
		setStyle("-fx-background-color: "+backgroundColor);
		
	}


	public void toggleRefGrid(boolean b){
		if (b){
			makeGridLines();
		}
		else{
			this.getChildren().removeAll(myGridLines);
			myGridLines.clear();
		}
	}

	private void makeGridLines(){
		for (int i=0; i<myHeight; i+=translate){
			for (int j=0; j<myWidth; j+=translate){
				drawLine(i, j);
			}
		}
	}

	public void uploadMyBackgroundImage(Stage mainStage){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Turtle Image");
		fileChooser.setInitialDirectory(new File("./"));
		File file = fileChooser.showOpenDialog(mainStage);
		if(file != null&&(file.getName().contains(".JPG")||file.getName().contains(".png"))){
			ImageView myImage=new ImageView();
			BufferedImage buffer;
			try {
				buffer = ImageIO.read(file);
				Image img=SwingFXUtils.toFXImage(buffer, null);
				myImage.setImage(img);
				myImage.setFitHeight(myHeight);
				myImage.setFitWidth(myWidth);
				myImage.setVisible(true);
				this.getChildren().add(myImage);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Please select another file");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please select another file");
		}

		myImageView=new ImageView();
		BufferedImage buffer;
		try {
			buffer = ImageIO.read(file);
			Image img=SwingFXUtils.toFXImage(buffer, null);
			myImageView.setImage(img);
			myImageView.setFitHeight(myHeight);
			myImageView.setFitWidth(myWidth);
			myImageView.setVisible(true);
			this.getChildren().add(myImageView);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	private void drawLine(int y, int x){
		Line verticalGridLine=new Line(x, 0, x, myHeight);
		verticalGridLine.setStroke(Paint.valueOf("GREY"));
		verticalGridLine.setStyle("-fx-fill: GREY");
		Line horizontalGridLine=new Line(0, y, myWidth, y);
		horizontalGridLine.setStroke(Paint.valueOf("GREY"));
		this.getChildren().addAll(verticalGridLine, horizontalGridLine);
		myGridLines.add(verticalGridLine);
		myGridLines.add(horizontalGridLine);
	}

	private int translateX(int number){
		return number*translate;
	}
	private int translateY(int number){
		return myHeight-(number*translate);
	}
	public void drawLine(int startX, int startY, int endX, int endY, String myColor){
		Line myLine=new Line(startX, startY, endX, endY);
		myLine.setStroke(Paint.valueOf(myColor));
		myLines.push(myLine);
		getChildren().add(myLine);
	}

	public void clear(){
		this.getChildren().removeAll(myLines);
	}

	private void undoLine(){
		this.getChildren().remove(myLines.pop());
	}

	public void gridMoveTurtle(int x, int y){
		myTurtle.move(translateX(x),translateY(y));
	}
	public void moveTurtle(int x, int y){
		myTurtle.relocate(x, y);
	}
	public void undo(int x, int y){
		moveTurtle(x,y);
		undoLine();
	}
}

