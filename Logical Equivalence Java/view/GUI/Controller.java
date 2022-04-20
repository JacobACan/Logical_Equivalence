package view.GUI;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import model.LogicalEquivalenceException;
import model.Logical_Equivalence;
import model.Operators.OperatorType;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button solveButton;
    public Label result;
    public TextField equation1;
    public TextField equation2;
    private Logical_Equivalence logical_equivalence;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logical_equivalence = new Logical_Equivalence();
    }

    public void setEquation1() {
        String convertedUserInput = convertUserInputEquation(equation1.getText());
        try {
            logical_equivalence.setEquation1(convertedUserInput);
            equation1.setStyle("-fx-background-color: green; ");
        } catch (LogicalEquivalenceException e) {
            equation1.setStyle("-fx-background-color: red; ");
        }
        solveEquivalence();
    }

    public void setEquation2() {
        String convertedUserInput = convertUserInputEquation(equation2.getText());
        try {
            logical_equivalence.setEquation2(convertedUserInput);
            equation2.setStyle("-fx-background-color: green; ");
        } catch (LogicalEquivalenceException e) {
            equation2.setStyle("-fx-background-color: red; ");
        }
        solveEquivalence();
    }

    public void solveEquivalence() {
        try {
            logical_equivalence.evaluate();
        } catch (LogicalEquivalenceException e) {
            result.setText("Result: " + e);
        }
        result.setText("Result: " + Arrays.toString(logical_equivalence.getTruthTable()));
    }


    private static String convertUserInputEquation (String userInput) {
        String convertedString = userInput.replace("and", OperatorType.AND.toString())
                .replace("or", OperatorType.OR.toString())
                .replace("iff", OperatorType.IFF.toString())
                .replace("if", OperatorType.IF.toString())
                .replace("not", OperatorType.NOT.toString());
        return convertedString;
    }

}
