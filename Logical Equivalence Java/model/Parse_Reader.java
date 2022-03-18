package model;

import java.util.ArrayList;
import java.util.List;


public class Parse_Reader {
    private static int size = 0;
    private static char[] operators = {};
    private static char[] propositions = {'p', 'q', 'r', 's'};

    public static Operator parseReader(String parsedString) {
        size = findSize(parsedString);
        
    }

    private static int findSize(String parsedString) {
        //2^n
        int n = 0;
        char[] parsedCharArray = parsedString.toCharArray();
        for (char ch: parsedCharArray) {
            boolean contains = false;
            for (char proposition : propositions) {
                if (proposition == ch) contains = true;
            }
            if (contains) n++;
        }

        return (int) Math.pow(2, n);
    }
}
