package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Logical_EquivalenceGUI extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox(); 

        stage.setScene(new Scene(new Label()));
        stage.setTitle("Template");
        stage.show();
    }
    private Button makeButton(String innerText) {
        Button button = new Button(innerText);

        return button;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
