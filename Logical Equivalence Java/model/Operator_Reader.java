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

    //can revise with 

    public Operator_Reader(String inputString) {
        this.inputString = inputString;
        removeSpaces();

        this.propositions = new HashSet<>();
        setPropositions();

        this.orderOfOperations = new ArrayList<>();
        setOrderOfOperations();
        
        this.validInput = checkParenthesis(inputString);

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
        orderOfOperations.add("≡");
        orderOfOperations.add("↔");
        orderOfOperations.add("→");
        orderOfOperations.add("v");
        orderOfOperations.add("^");
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
                            if (operator.equals("¬"))notOperatorFound = true;

                            leftString = inputString.substring(0, i);
                            rightString = inputString.substring(i+1, inputString.length());


                            if (propositions.contains(leftString)) { // compile left string to proposition 
                                parsedLeftString = leftString;
                            } else {
                                if (leftString != "") parsedLeftString = parseString(leftString);
                            }

                            if (propositions.contains(rightString)) { // compile right string to proposition
                                if (rightString != "") parsedRightString = rightString;
                            } else {
                                parsedRightString = parseString(rightString);
                            }
                        } 
                    }
                    i--;
                }
                

                if (notOperatorFound) return String.format("%s%s", operator, parsedRightString);
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
}
