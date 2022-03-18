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

    public static Unit parseReader(String parsedString) {
        findSize(parsedString);
        int i = 0;
        Unit parsedStringAsUnit = makeParsedString(parsedString, i);
        return parsedStringAsUnit;
    }

    private static Unit makeParsedString(String parsedString, int i) {
        int propositionNumber = 1;
        Map<Character, Integer> propositionsNumbers = new HashMap<>();


        if (parsedString.charAt(i) == '≡') {
            i += 3;
            return new Operator_LOGICALLY_EQUAL(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == '↔') {
            i += 3;
            return new Operator_IFF(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == '→') {
            i += 3;
            return new Operator_IF(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == 'v') {
            i += 3;
            return new Operator_OR(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == '^') {
            i += 3;
            return new Operator_AND(size, makeParsedString(parsedString, i+1), makeParsedString(parsedString, i+2));

        } else if (parsedString.charAt(i) == '¬') {
            i+= 2;
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
        //2^n
        int n = 0;
        char[] parsedCharArray = parsedString.toCharArray();
        for (char ch: parsedCharArray) {
            if (Character.isAlphabetic(ch)) n++;
        }

        size = (int) Math.pow(2, n);
    }
}
