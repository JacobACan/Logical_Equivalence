package model.Operators;

public class Operator_PROPOSITION extends Operator{
    private int propositionNumber;
    public Operator_PROPOSITION(int size, int propositionNumber) {
        super(size);
        this.propositionNumber = propositionNumber;
        evaluate();
    }

    @Override
    public void evaluate() {
        int i = 0;
        int truthSwitchLengt = (int) Math.pow(2, propositionNumber);
        while(i < truthTable.length) {
            for(int j = 0; j < truthSwitchLengt; j++) {
                truthTable[i] = false;
                i++;
            }
            for(int j = 0; j < truthSwitchLengt; j++) {
                truthTable[i] = true;
                i++;
            }
        }
    }
}