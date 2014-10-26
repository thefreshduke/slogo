package GUIFunctions;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class VariableTable extends TableView {
	ArrayList<Variable> myVariables;
	public VariableTable(){
		myVariables=new ArrayList();
		Scene scene = new Scene(new Group());
		Stage newStage=new Stage();
		newStage.setTitle("Variable Table");
		newStage.setWidth(300);
		newStage.setHeight(500);
		Label label = new Label("My Variable Table");
		label.setFont(new Font("Arial", 20));
		Timeline time=new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(build());
		time.play();
		setEditable(true);
		TableColumn myVariableName=new TableColumn("Variable Name");
		TableColumn myValue=new TableColumn("My Value");
		myValue.setCellValueFactory(new Callback<CellDataFeatures<Variable, Double>, ObservableValue<Double>>() {
			@Override
			public ObservableValue<Double> call(
					CellDataFeatures<Variable, Double> myData) {
				return new ReadOnlyObjectWrapper<Double> (myData.getValue().getValue());
			}
		});

		myVariableName.setCellValueFactory(new Callback<CellDataFeatures<Variable, Double>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(
					CellDataFeatures<Variable, Double> myData) {
				return new ReadOnlyObjectWrapper<String> (myData.getValue().getName());
			}
		});


		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.getChildren().addAll(label, this);
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		newStage.setScene(scene);
		newStage.show();

		getColumns().addAll(myVariableName, myValue);
		newStage.show();
	}

	private KeyFrame build() {
		Duration myDuration=Duration.millis(10);
		KeyFrame myFrame=new KeyFrame(myDuration, new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				update();
			}
		});
		return myFrame;
	}

	public void addVariables(ArrayList<Variable> myVars){
		myVariables=myVars;
	}

	public void update(){
		this.getItems().clear();
		ObservableList<Variable> myObservableList=FXCollections.observableArrayList();
		this.getItems().clear();
		for (Variable myVar: myVariables){
			myObservableList=FXCollections.observableArrayList();
			myObservableList.add(myVar);
		}
		this.setItems((myObservableList));
	}

}

