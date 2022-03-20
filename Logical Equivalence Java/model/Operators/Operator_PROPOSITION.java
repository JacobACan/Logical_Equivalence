package model.Operators;

import java.util.Arrays;

public class Operator_PROPOSITION extends Operator{
    private int propositionNumber;
    private String proposition;
    public Operator_PROPOSITION(int size, int propositionNumber, String proposition) {
        super(size);
        this.propositionNumber = propositionNumber;
        this.proposition = proposition;
        evaluate();
    }

    @Override
    public void evaluate() {
        int i = 0;
        int truthSwitchLength = size / ((int) Math.pow(2, propositionNumber) * 2);
        while(i < truthTable.length) {
            for(int j = 0; j < truthSwitchLength; j++) {
                truthTable[i] = false;
                i++;
            }
            for(int j = 0; j < truthSwitchLength; j++) {
                truthTable[i] = true;
                i++;
            }
        }
    }
    public String toString() {
        return String.format("%s: %s", proposition, Arrays.toString(truthTable));
    }
}
