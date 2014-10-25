package GUIFunctions;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpPage implements GUIFunction {
    public HelpPage () {

    }

    @Override
    public void doAction () {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        // webEngine.load("./resources/helpInfo/commands.PHP");
        webEngine
                .load("http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php");
        Stage helpStage = new Stage();
        Scene scene = new Scene(browser, 700, 480);
        helpStage.setScene(scene);
        helpStage.show();
    }

    @Override
    public void doAction (List<Number> newVal) {
        // TODO Auto-generated method stub

    }
}
