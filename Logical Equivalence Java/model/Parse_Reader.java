package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Operators.OperatorType;
import model.Operators.Operator_AND;
import model.Operators.Operator_IF;
import model.Operators.Operator_IFF;
import model.Operators.Operator_LOGICALLY_EQUAL;
import model.Operators.Operator_NOT;
import model.Operators.Operator_OR;
import model.Operators.Operator_PROPOSITION;
import model.Operators.Unit;


public class Parse_Reader {
    private int size;
    private int propositionNumber;
    private Map<Character, Integer> propositionsNumbers;
    private int pointer;
    private Unit unit;

    public Parse_Reader(String parsedString) {
        this.size = 0;
        this.propositionNumber = 0;
        this.propositionsNumbers = new HashMap<>();
        this.pointer = 0;

        this.unit = readParsedString(parsedString);
    }

    public Unit getUnit() {
      return unit;
    }

    private Unit readParsedString(String parsedString) {
        if (parsedString == null) return null;
        pointer = 0;
        size = 0;
        propositionNumber = 0;
        propositionsNumbers = new HashMap<>();

        findSize(parsedString);
        findPropositionNumber(parsedString);
        Unit parsedStringAsUnit = makeParsedString(parsedString, pointer);
        return parsedStringAsUnit;
    }

    private Unit makeParsedString(String parsedString, int i) {


        if (parsedString.charAt(i) == OperatorType.LOGICALLY_EQUAL.toString().charAt(0)) { //if parsedString[i] logically equal
            return new Operator_LOGICALLY_EQUAL(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == OperatorType.IFF.toString().charAt(0)) { //if parsedString[i] iff
            return new Operator_IFF(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == OperatorType.IF.toString().charAt(0)) { //if parsedString[i] if
            return new Operator_IF(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == OperatorType.OR.toString().charAt(0)) { //if parsedString[i] or
            return new Operator_OR(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == OperatorType.AND.toString().charAt(0)) { //if parsedString[i] and
            return new Operator_AND(size, makeParsedString(parsedString, ++pointer), makeParsedString(parsedString, ++pointer));

        } else if (parsedString.charAt(i) == OperatorType.NOT.toString().charAt(0)) { //if parsedString[i] not
            return new Operator_NOT(size, makeParsedString(parsedString, ++pointer));

        } else if ( OperatorType.PROPOSITION.toString().contains(Character.toString(parsedString.charAt(pointer))) ) {
            return new Operator_PROPOSITION(size, propositionsNumbers.get(parsedString.charAt(pointer)));

        } else {
            return null;
        }
    }

    private void findSize(String parsedString) {
        //size = 2^n
        Set<Character> propositions = new HashSet<>();
        char[] parsedCharArray = parsedString.toCharArray();
        for (char ch: parsedCharArray) {
            if (Character.isAlphabetic(ch)) propositions.add(ch);
        }
        size = (int) Math.pow(2, propositions.size());
    }
    private void findPropositionNumber(String parsedString) {
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

    @Override
    public String toString() {
        return this.unit.getTruthTable().toString();
    }


    public static void main(String[] args) {
        Equation_Parser string1 = new Equation_Parser("p ∧ q"); //Good 
        Equation_Parser string2 = new Equation_Parser("p ∨ q"); //Good
        Equation_Parser string3 = new Equation_Parser("p → q"); //Good
        Equation_Parser string4 = new Equation_Parser("p ↔ q"); //Bad
        Equation_Parser string5 = new Equation_Parser("p ≡ q"); //Bad
        Equation_Parser string6 = new Equation_Parser("p ∧ ¬q"); //Good

        Equation_Parser string10 = new Equation_Parser("¬(¬r ↔ (q → s) ^ q v p) ≡ (¬r ^ (¬q → ¬s) ^ q ^ ¬p)");


        Parse_Reader parseReader1 = new Parse_Reader(string1.toString());
        Parse_Reader parseReader2 = new Parse_Reader(string2.toString());
        Parse_Reader parseReader3 = new Parse_Reader(string3.toString());
        Parse_Reader parseReader4 = new Parse_Reader(string4.toString());
        Parse_Reader parseReader5 = new Parse_Reader(string5.toString());
        Parse_Reader parseReader6 = new Parse_Reader(string6.toString());

        Parse_Reader parseReader10 = new Parse_Reader(string10.toString());

        System.out.println("x");
    }
}
