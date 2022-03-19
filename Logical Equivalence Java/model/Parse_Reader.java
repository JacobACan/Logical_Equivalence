package model;

import java.util.HashMap;
import java.util.Map;
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
    private static int propositionNumber = 1;
    private static Map<Character, Integer> propositionsNumbers = new HashMap<>();

    public static Unit parseReader(String parsedString) {
        findSize(parsedString);
        int i = 0;
        Unit parsedStringAsUnit = makeParsedString(parsedString, i);
        return parsedStringAsUnit;
    }

    private static Unit makeParsedString(String parsedString, int i) {


        if (parsedString.charAt(i) == '≡') {
            return new Operator_LOGICALLY_EQUAL(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == '↔') {
            return new Operator_IFF(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == '→') {
            return new Operator_IF(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == 'v') {
            return new Operator_OR(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == '^') {
            return new Operator_AND(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == '¬') {
            return new Operator_NOT(size, makeParsedString(parsedString, i+1));

        } else if (Character.isAlphabetic(parsedString.charAt(i))) {
            if (!propositionsNumbers.containsKey(parsedString.charAt(i))) propositionsNumbers.put(parsedString.charAt(i), propositionNumber);
            propositionNumber++;

            return new Operator_PROPOSITION(size, propositionsNumbers.get(parsedString.charAt(i)));
        } else {
            return null;
        }
    }

    private static void findSize(String parsedString) {
        //size = 2^n
        int n = 0;
        char[] parsedCharArray = parsedString.toCharArray();
        for (char ch: parsedCharArray) {
            if (Character.isAlphabetic(ch)) n++;
        }
        size = (int) Math.pow(2, n);
    }

    public static void main(String[] args) {
        Equation_Parser string5 = new Equation_Parser("¬(¬r ↔ (q → s) ^ q ^ p) ≡ (¬r ^ (¬q → ¬s) ^ q ^ ¬p)");
        Unit logicEQ1 = parseReader(string5.getParsedString());

        System.out.println("x");
    }
}
