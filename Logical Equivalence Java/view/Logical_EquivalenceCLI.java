package view;

import java.util.Scanner;

import model.LogicalEquivalenceException;
import model.Logical_Equivalence;
import model.Operators.OperatorType;

public class Logical_EquivalenceCLI {
    public static String convertUserInputEquation (String userInput) {
        String convertedString = userInput.replace("and", OperatorType.AND.toString())
        .replace("or", OperatorType.OR.toString())
        .replace("iff", OperatorType.IFF.toString())
        .replace("if", OperatorType.IF.toString())
        .replace("not", OperatorType.NOT.toString());
        return convertedString;
    }
    public static boolean setEquation(Logical_Equivalence logical_Equivalence, int equationNumber) {
        boolean inValidEquation = true;
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while(inValidEquation) {
            System.out.print(String.format("\nEnter Valid Equation %d: ", equationNumber));
            userInput = scanner.nextLine();
            if (scannerOptionQuit(userInput)) return false;
            String convertedUserInputString = convertUserInputEquation(userInput);
            try {
                if (equationNumber == 1) {
                    logical_Equivalence.setEquation1(convertedUserInputString);
                } else if (equationNumber == 2) {
                    logical_Equivalence.setEquation2(convertedUserInputString);
                }
                inValidEquation = false;
            } catch (LogicalEquivalenceException e) {
                System.out.println(e);
            }
            i++;
        }
        
        return true;
    }
    public static void printEquationsAndEquivalence(Logical_Equivalence logical_Equivalence) {
        System.out.println(String.format("\n%s", logical_Equivalence.toString()));
    }
    public static boolean testLogicalEquivalence(Logical_Equivalence logical_Equivalence) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter a command: ");
        String userInput = scanner.nextLine().strip();
        if (scannerOptionQuit(userInput)) {
            scanner.close();
            return false;
        } else if (userInput.toLowerCase().equals("help")) {
            help();
        } else if (userInput.toLowerCase().equals("equation")) {
            if (!setEquation(logical_Equivalence, 1)) return endProgram(scanner);
            if (!setEquation(logical_Equivalence, 2)) return endProgram(scanner);
            try{
                logical_Equivalence.evaluate();
            } catch (LogicalEquivalenceException e) {
                System.out.println(e);
            }
            printEquationsAndEquivalence(logical_Equivalence);
        } else {
            System.out.println("Command Not Recognized...\n-help : for help");
        }
        
        // scanner.close();
        return true;
    }
    public static boolean scannerOptionQuit(String userInput) {
        if (userInput.toLowerCase().equals("quit")) {
            System.out.println("quitting...");
            return true;
        }
        return false;
    }
    public static boolean endProgram(Scanner scanner) {
        scanner.close();
        return false;
    }
    public static void help() {
        System.out.println(String.format("\n-----HELP MENU-----\n\t-equation : to test logical equivalence\n\t\t-ex : (p and not q) iff r\n\t-quit : to quit\n\t-help : displays this message.\n____________________"));
    }


    public static void main(String[] args) {
        Logical_Equivalence logical_Equivalence = new Logical_Equivalence();
        help();
        while(testLogicalEquivalence(logical_Equivalence));    
    }
}