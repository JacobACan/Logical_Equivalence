package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Operator_Reader {
    private String inputString;
    private String compiledString;
    private boolean validInput;
    private List<String> orderOfOperations;
    private  Set<String> propositions = new HashSet<>();

    public Operator_Reader(String inputString) {
        this.inputString = inputString;
        removeSpaces();

        this.propositions = new HashSet<>();
        setPropositions();

        this.orderOfOperations = new ArrayList<>();
        setOrderOfOperations();
        
        this.validInput = checkParenthesis();

        this.compiledString = "";
        parseInput();
    }
    
    
    private void setPropositions() {
        //{'p','q','r','s'};
        propositions.add("p");
        propositions.add("q");
        propositions.add("r");
        propositions.add("s");
    }
    private void setOrderOfOperations() {
        orderOfOperations.add("↔");
        orderOfOperations.add("→");
        orderOfOperations.add("v");
        orderOfOperations.add("^");
        orderOfOperations.add("¬");
    }

    private boolean checkParenthesis() {
        int leftParenthesis = 0;
        int rightParenthesis = 0;
        char[] inputStringArray = this.inputString.toCharArray();
        for (char ch: inputStringArray) {
            if (ch == '(') {
                leftParenthesis++;
            }
            if (ch == ')') {
                rightParenthesis++;
            }
        }
        if (leftParenthesis == rightParenthesis) return true;
        return false;
    }
    
    private void removeSpaces() {
        String removedSpacesString = "";
        char[] inputStringArray = this.inputString.toCharArray();
        for (char ch: inputStringArray) {
            if (ch != ' ') {
                removedSpacesString += Character.toString(ch);
            }
        }
        this.inputString = removedSpacesString;
    }
    public void compileString() {
        this.compiledString = parseString(this.inputString);
    }

    public void parseInput() {        
        if (validInput) {
            this.compiledString = parseString(this.inputString);
        } else {
            this.compiledString = "Invalid Input";
        }
    }


    private String parseString(String inputString) {
        boolean insideParenthesis = false;
        boolean operatorFound = false;
        String leftString  = "";
        String rightString  = "";
        String compiledLeftString = "";
        String compiledRightString = "";

        

        for (String operator : orderOfOperations) {
            int i = inputString.length() - 1;
            char[] inputcharArray = inputString.toCharArray();

            if (!operatorFound) {                                       // find first occurance of lowest operator
                while(i >= 0 && !operatorFound) {
                    if(inputcharArray[i] == '(') {                       //skip parenthesis
                        insideParenthesis = true;
                    } else if (inputcharArray[i] == ')') {
                        insideParenthesis = false;
                    }
                    if(!insideParenthesis) {
                        if(String.format("%c", inputcharArray[i]).equals(operator)) { // if operator found
                            operatorFound = true;
                            leftString = searchLeft(inputcharArray, i);
                            rightString = searchRight(inputcharArray, i);

                            if (propositions.contains(leftString)) { // compile left string to proposition 
                                compiledLeftString = leftString;
                            } else {
                                compiledLeftString = parseString(leftString);
                            }

                            if (propositions.contains(rightString)) { // compile right string to proposition
                                compiledRightString = rightString;
                            } else {
                                compiledRightString = parseString(rightString);
                            }
                        } 
                    }
                    i--;
                }
                if (operatorFound) return (String.format("%s%s%s", operator, compiledLeftString, compiledRightString));
            }
        }
        return "";
    }
    

    public String searchLeft(char[] inputcharArray, int indexOfOperator) {
        String leftString = "";
        List<String> leftOfOperator = new ArrayList<>();
        for (int i = 0; i < indexOfOperator; i++) {// Add to left of operator list
            leftOfOperator.add(Character.toString(inputcharArray[i]));
        }
        

        if (propositions.contains(leftOfOperator.get(leftOfOperator.size() -1)) && leftOfOperator.size() == 1) { // if left is a singular proposition
            return leftOfOperator.get(0);
        } else if (leftOfOperator.get(leftOfOperator.size() - 1).equals(")") && leftOfOperator.get(0).equals("(")) { // if left is only parenthesis
            for (int i = 1; i < leftOfOperator.size() -1; i++) {
                leftString += leftOfOperator.get(i);
            }
        } else { // needs to be broken down further therefore return entire left
            for (String element : leftOfOperator) {
                leftString += element;
            }
        }


        return leftString;
    }
    
    public String searchRight(char[] inputcharArray, int indexOfOperator) {
        String rightString = "";
        List<String> rightOfOperator = new ArrayList<>();
        for (int i = indexOfOperator + 1; i < inputcharArray.length; i++) {// Add to right of operator list
            rightOfOperator.add(Character.toString(inputcharArray[i]));
        }
        

        if (propositions.contains(rightOfOperator.get(rightOfOperator.size() - 1)) && rightOfOperator.size() == 1) { // if right is a singular proposition
            return rightOfOperator.get(0);
        } else if (rightOfOperator.get(0).equals("(") && rightOfOperator.get(rightOfOperator.size() - 1).equals(")")) { // if right is only parenthesis
            for (int i = 1; i < rightOfOperator.size() -1; i++) {
                rightString += rightOfOperator.get(i);
            }
        } else { // needs to be broken down further therefore return entire left
            for (String element : rightOfOperator) {
                rightString += element;
            }
        }
        
        return rightString;
    }   

    @Override
    public String toString() {
        return compiledString;
    }
}
