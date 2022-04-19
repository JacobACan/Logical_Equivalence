package view.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Logical_EquivalenceGUI extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Logical_Equivalence.fxml"));
        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setTitle("Logical Equivalence Calculator");
        stage.setScene(scene);
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
