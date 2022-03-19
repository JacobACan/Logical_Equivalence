package model;

import java.util.ArrayList;
import java.util.List;

public class Equation_Parser {
    private String inputString;
    private String compiledString;
    private boolean validInput;
    private List<String> orderOfOperations;
    

    //can revise with 

    public Equation_Parser(String inputString) {
        this.inputString = inputString;
        removeSpaces();

        this.orderOfOperations = new ArrayList<>();
        setOrderOfOperations();
        
        this.validInput = checkParenthesis(inputString);

        this.compiledString = "";
        parseInput();
    }
    
    private void setOrderOfOperations() {
        orderOfOperations.add("≡");
        orderOfOperations.add("↔");
        orderOfOperations.add("→");
        orderOfOperations.add("∨");
        orderOfOperations.add("∧");
        orderOfOperations.add("¬");
    }

    private boolean checkParenthesis(String inputString) {
        int leftParenthesis = 0;
        int rightParenthesis = 0;
        char[] inputStringArray = inputString.toCharArray();
        for (char ch: inputStringArray) {
            if (ch == '(') {
                leftParenthesis++;
            }
            if (ch == ')') {
                rightParenthesis++;
                if (rightParenthesis > leftParenthesis) return false;
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
    private String removeOutsideParenthesis(String string) {
        String removedParenthesisString = string;
        char[] StringArray = string.toCharArray();
        if (string.length() == 0) return removedParenthesisString;
        if (StringArray[StringArray.length - 1] == ')' &&  StringArray[0] == '(' ) {

            StringArray[StringArray.length - 1] = ' ';
            StringArray[0] = ' ';

            removedParenthesisString = "";
            for (char ch: StringArray) {
                if (ch != ' ') {
                    removedParenthesisString += Character.toString(ch);
                }
            }
        }
        return removedParenthesisString;
    }

    private void parseInput() {        
        if (validInput) {
            this.compiledString = parseString(this.inputString);
            if (compiledString.equals("")) compiledString = "Invalid Input";
            if (compiledString.equals("Invalid String")) compiledString = "Invalid Input";
        } else {
            this.compiledString = "Invalid Input";
        }
    }


    private String parseString(String inputString) {
        boolean insideParenthesis = false;
        boolean operatorFound = false;
        boolean notOperatorFound = false;
        String leftString  = "";
        String rightString  = "";
        String parsedLeftString = "";
        String parsedRightString = "";
        

        

        for (String operator : orderOfOperations) {
            int rightParenthesis = 0;
            int leftParenthesis = 0;
            if (checkParenthesis(removeOutsideParenthesis(inputString))) inputString = removeOutsideParenthesis(inputString); //if valid parenthesis after string removed
            int i = inputString.length() - 1;

            char[] inputcharArray = inputString.toCharArray();

            if (!operatorFound) {                                       // find first occurance of lowest operator
                while(i >= 0 && !operatorFound) {
                    if(inputcharArray[i] == ')') {                       //skip parenthesis
                        rightParenthesis++;
                        insideParenthesis = true;
                        
                    } else if (inputcharArray[i] == '(') {
                        leftParenthesis++;
                        if (rightParenthesis == leftParenthesis) insideParenthesis = false;
                    }
                    
                    if(!insideParenthesis) {        // skip parenthesis to parse them last
                        if(String.format("%c", inputcharArray[i]).equals(operator)) { // if operator found
                            operatorFound = true;
                            if (operator.equals("¬")){
                                notOperatorFound = true;
                                leftString = inputString.substring(0, i);
                                if (inputString.length()-leftString.length() > 1) rightString = inputString.substring(i+1, inputString.length());
                            } else {
                                leftString = inputString.substring(0, i);
                                rightString = inputString.substring(i+1, inputString.length());
                            }



                            if (leftString.length() == 1 && Character.isAlphabetic(leftString.charAt(0))) { // compile left string to proposition 
                                parsedLeftString = leftString;
                            } else {
                                if (leftString != "") parsedLeftString = parseString(leftString);
                            }

                            if (rightString.length() == 1 && Character.isAlphabetic(rightString.charAt(0))) { // compile right string to proposition
                                if (rightString != "") parsedRightString = rightString;
                            } else {
                                parsedRightString = parseString(rightString);
                            }
                        } 
                    }
                    i--;
                }
                


                if (notOperatorFound && rightString!= "") return String.format("%s%s", operator, parsedRightString);
                if (inputString.length() == 1 && Character.isAlphabetic(inputString.charAt(0))) return inputString;
                if (operatorFound && (parsedLeftString.equals("") || parsedRightString.equals(""))) return "Invalid String";
                if (operatorFound && (parsedLeftString == "Invalid String" || parsedRightString == "Invalid String")) return "Invalid String";
                if (operatorFound) return (String.format("%s%s%s", operator, parsedLeftString, parsedRightString));
                
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return compiledString;
    }

    public String getParsedString() {
        return compiledString;
    }


    public static void main(String[] args) {
        
        System.out.println("");
    }
}
