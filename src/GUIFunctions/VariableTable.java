package GUIFunctions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class VariableTable extends TableView {
	private ArrayList<UserInput> myVariables;
	public VariableTable(){
		myVariables=new ArrayList<UserInput>();
		Scene scene = new Scene(new Group());
		Stage newStage=new Stage();
		newStage.setTitle("Variable Table");
		newStage.setWidth(500);
		newStage.setHeight(500);
		Label label = new Label("My Variable Table");
		label.setFont(new Font("Arial", 20));

		setEditable(false);
		TableColumn myVariableColumn=new TableColumn("Variable");
		TableColumn myVariableName=new TableColumn("Name");
		TableColumn myValue=new TableColumn("Value");

		myValue.setCellValueFactory(new Callback<CellDataFeatures<UserInput, Double>, ObservableValue<Double>>() {
			@Override
			public ObservableValue<Double> call(
					CellDataFeatures<UserInput, Double> myData) {
				if (myData.getValue() instanceof Variable){
					Variable myVariable=(Variable)myData.getValue();
					return new ReadOnlyObjectWrapper<Double> (myVariable.getValue());
				}
				return null;
			}
		});


		myVariableName.setCellValueFactory(new Callback<CellDataFeatures<UserInput, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserInput, String> myData) {
				if (myData.getValue() instanceof Variable){
					return new ReadOnlyObjectWrapper<String> (myData.getValue().getName());
				}
				return null;
			}
		});

		myVariableColumn.getColumns().addAll(myVariableName, myValue);

		TableColumn myUserFunctions=new TableColumn("User Functions");

		myUserFunctions.setPrefWidth(200);

		myUserFunctions.setCellValueFactory(new Callback<CellDataFeatures<UserInput, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(
					CellDataFeatures<UserInput, String> myData) {
				if (myData.getValue() instanceof Function){
					return new ReadOnlyObjectWrapper<String> (myData.getValue().getName());
				}
				return null;
			}
		});
		
		Timeline time=new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.getKeyFrames().add(build());
		time.play();
		
		getColumns().addAll(myVariableColumn, myUserFunctions);
		((Group) scene.getRoot()).getChildren().addAll(this);
		newStage.setScene(scene);
		newStage.show();
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

	public void addVariables(List<UserInput> myFunctions){
		myVariables.clear();
		myVariables=(ArrayList<UserInput>) myFunctions;
		
	}
	public void addInputs(List<UserInput> myFunctions){
		
	}

	public void update(){
		getItems().clear();
		ObservableList<UserInput> myObservableList=FXCollections.observableArrayList();
		for (UserInput myVar: myVariables){
			myObservableList.add(myVar);
		}
		setItems((myObservableList));
	}

}

