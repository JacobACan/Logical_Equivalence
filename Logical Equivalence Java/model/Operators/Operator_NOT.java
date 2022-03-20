package model.Operators;

import java.util.Arrays;

public class Operator_NOT extends Operator{
    private Unit rightUnit;
    public Operator_NOT(int size, Unit rightUnit) {
        super(size);
        this.rightUnit = rightUnit;
        evaluate();
    }

    @Override
    public void evaluate() {
        boolean[] truthTable1 = rightUnit.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (!truthTable1[i]);
            i++;
        }
    }
    // @Override
    // public String toString() {
    //     return String.format("left: %s\n%s\n", rightUnit ,Arrays.toString(truthTable));
    // }
}
