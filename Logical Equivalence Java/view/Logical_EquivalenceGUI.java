package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Logical_EquivalenceGUI extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Button button = makeButton("Evaluate");

        HBox hBox = new HBox(button); 

        
        stage.setScene(new Scene(hBox));
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
