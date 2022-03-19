package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Operators.Operator_AND;
import model.Operators.Operator_IF;
import model.Operators.Operator_IFF;
import model.Operators.Operator_LOGICALLY_EQUAL;
import model.Operators.Operator_NOT;
import model.Operators.Operator_OR;
import model.Operators.Operator_PROPOSITION;
import model.Operators.Unit;


public class Parse_Reader {
    private static int size = 0;
    private static char[] operators = {'≡', '↔', '→', 'v', '^', '¬'};
    private static int propositionNumber = 0;
    private static Map<Character, Integer> propositionsNumbers = new HashMap<>();
    private static int pointer = 0;

    public static Unit parseReader(String parsedString) {
        findSize(parsedString);
        findPropositionNumber(parsedString);
        Unit parsedStringAsUnit = makeParsedString(parsedString, pointer);
        return parsedStringAsUnit;
    }

    private static Unit makeParsedString(String parsedString, int i) {


        if (parsedString.charAt(i) == '≡') {
            return new Operator_LOGICALLY_EQUAL(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == '↔') {
            return new Operator_IFF(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == '→') {
            return new Operator_IF(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == '∨') {
            return new Operator_OR(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == '∧') {
            return new Operator_AND(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == '¬') {
            return new Operator_NOT(size, makeParsedString(parsedString, ++pointer));

        } else if (Character.isAlphabetic(parsedString.charAt(pointer))) {
            return new Operator_PROPOSITION(size, propositionsNumbers.get(parsedString.charAt(pointer)));

        } else {
            return null;
        }
    }

    private static void findSize(String parsedString) {
        //size = 2^n
        Set<Character> propositions = new HashSet<>();
        char[] parsedCharArray = parsedString.toCharArray();
        for (char ch: parsedCharArray) {
            if (Character.isAlphabetic(ch)) propositions.add(ch);
        }
        size = (int) Math.pow(2, propositions.size());
    }
    private static void findPropositionNumber(String parsedString) {
        char[] parsedCharArray = parsedString.toCharArray();
        for (char ch: parsedCharArray) {
            if (Character.isAlphabetic(ch)) {
                if (!propositionsNumbers.containsKey(ch)) {
                    propositionsNumbers.put(ch, propositionNumber);
                    propositionNumber++;
                }
            }
        }
    }


    public static void main(String[] args) {
        // Equation_Parser string1 = new Equation_Parser("p ∧ q"); 
        Equation_Parser string2 = new Equation_Parser("p ∨ q");
        Equation_Parser string3 = new Equation_Parser("p → q");//
        Equation_Parser string4 = new Equation_Parser("p ↔ q");
        Equation_Parser string5 = new Equation_Parser("p ≡ q");
        Equation_Parser string6 = new Equation_Parser("p ∧ ¬q"); 
        // Equation_Parser string10 = new Equation_Parser("¬(¬r ↔ (q → s) ^ q v p) ≡ (¬r ^ (¬q → ¬s) ^ q ^ ¬p)");

        // Unit logicEQ1 = parseReader(string1.getParsedString());
        Unit logicEQ2 = parseReader(string2.getParsedString());
        Unit logicEQ3 = parseReader(string3.getParsedString());
        Unit logicEQ4 = parseReader(string4.getParsedString());
        Unit logicEQ5 = parseReader(string5.getParsedString());
        Unit logicEQ6 = parseReader(string6.getParsedString());
        // Unit logicEQ10 = parseReader(string10.getParsedString());

        System.out.println("x");
    }
}
