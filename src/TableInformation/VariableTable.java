package TableInformation;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import GUIFunctions.Function;
import GUIFunctions.UserInput;
import GUIFunctions.Variable;
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

public class VariableTable extends Pane {
	private ArrayList<UserInput> myVariables;
	private final static Dimension DEFAULT_SIZE=new Dimension(500,500);
	private ArrayList<UserInput> myFunctions;
	TableView variables;
	TableView functions;
	public VariableTable(){
		variables=new TableView();
		functions=new TableView();
		myVariables=new ArrayList<UserInput>();
		myFunctions=new ArrayList<UserInput>();
		
		Scene scene = new Scene(new Group());
		Stage newStage=new Stage();
		newStage.setTitle("Variable Table");
		newStage.setWidth(DEFAULT_SIZE.width);
		newStage.setHeight(DEFAULT_SIZE.height);
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
		variables.getColumns().addAll(myVariableColumn);
		functions.getColumns().addAll(myUserFunctions);
		((Group) scene.getRoot()).getChildren().addAll(this);
		newStage.setScene(scene);
		newStage.show();
		variables.relocate(0, 0);
		variables.setPrefSize(DEFAULT_SIZE.width/2, DEFAULT_SIZE.height);
		functions.setPrefSize(DEFAULT_SIZE.width/2, DEFAULT_SIZE.height);
		functions.relocate(DEFAULT_SIZE.width/2, 0);
		this.setPrefSize(DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		this.getChildren().addAll(variables, functions);
	}

	private KeyFrame build() {
		Duration myDuration=Duration.millis(10);
		KeyFrame myFrame=new KeyFrame(myDuration, new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				updateVariables();
				updateFunctions();
			}
		});
		return myFrame;
	}

	public void addVariables(List<UserInput> myUserInputFunctions){
		myVariables.clear();
		myVariables= (ArrayList<UserInput>) myUserInputFunctions;
		
	}
	public void addInputs(List<UserInput> myUserInputFunctions){
		myFunctions.clear();
		myFunctions=(ArrayList<UserInput>) myUserInputFunctions;
	}

	private void updateVariables(){
		variables.getItems().clear();
		ObservableList<UserInput> myObservableList=FXCollections.observableArrayList();
		for (UserInput myVar: myVariables){
			myObservableList.add(myVar);
		}
		variables.setItems((myObservableList));
	}
	private void updateFunctions(){
		functions.getItems().clear();
		ObservableList<UserInput> myObservableList=FXCollections.observableArrayList();
		for (UserInput myVar: myFunctions){
			myObservableList.add(myVar);
		}
		functions.setItems((myObservableList));
		
	}
	public void makeTableView(TableView myTable){
		myTable.setEditable(false);
	}

}

