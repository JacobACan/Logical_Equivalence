package view.GUI;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.LogicalEquivalenceException;
import model.Logical_Equivalence;
import model.Operators.OperatorType;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label title;
    public Button solveButton;
    public VBox result;
    public TextField equation1;
    public TextField equation2;
    private Logical_Equivalence logical_equivalence;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logical_equivalence = new Logical_Equivalence();
        title.setEffect(new DropShadow(10, Color.GRAY));
        equation1.setEffect(new DropShadow(5, Color.GRAY));
        equation2.setEffect(new DropShadow(5, Color.GRAY));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setOpacity(1);
    }

    public void setEquation1() {
        String convertedUserInput = convertUserInputEquation(equation1.getText());
        try {
            logical_equivalence.setEquation1(convertedUserInput);
            equation1.setStyle("-fx-background-color: green; ");
            solveEquivalence();
        } catch (LogicalEquivalenceException e) {
            equation1.setStyle("-fx-background-color: red; ");
        }
    }

    public void setEquation2() {
        String convertedUserInput = convertUserInputEquation(equation2.getText());
        try {
            logical_equivalence.setEquation2(convertedUserInput);
            equation2.setStyle("-fx-background-color: green; ");
            solveEquivalence();
        } catch (LogicalEquivalenceException e) {
            equation2.setStyle("-fx-background-color: red; ");
        }
    }

    public void solveEquivalence() {
        try {
            logical_equivalence.evaluate();
            setTruthTable();
        } catch (LogicalEquivalenceException e) {
        }
    }


    private static String convertUserInputEquation (String userInput) {
        String convertedString = userInput.replace("and", OperatorType.AND.toString())
                .replace("or", OperatorType.OR.toString())
                .replace("iff", OperatorType.IFF.toString())
                .replace("if", OperatorType.IF.toString())
                .replace("not", OperatorType.NOT.toString());
        return convertedString;
    }
    private void setTruthTable() {
        boolean[] truthTable = logical_equivalence.getTruthTable();
        GridPane truthTablePane = new GridPane();
        truthTablePane.add(new Label(convertUserInputEquation(equation1.getText())  + " " + OperatorType.LOGICALLY_EQUAL + " "), 0, 0);
        truthTablePane.add(new Label(convertUserInputEquation(equation2.getText())  + "\t::\t"), 1, 0);
        if (truthTable != null) {
            int rowLength = 20;
            int c = 0;
            for (int r = 0; r < truthTable.length ; r++) {
                Label logicalUnit = new Label("" + truthTable[r] + " ");
                if ("true".equals("" + truthTable[r])) {
                    logicalUnit.setTextFill(Color.GREEN);
                } else {
                    logicalUnit.setTextFill(Color.RED);
                }
                truthTablePane.add( logicalUnit , (r + 2) - (c * (rowLength)), c);
                c = r / rowLength;
            }
        }
        truthTablePane.setId("truthTable");
        truthTablePane.setEffect(new DropShadow(3, Color.GRAY));
        result.getChildren().add(truthTablePane);
        if (result.getChildren().size() > 6) result.getChildren().remove(0);
    }

}
