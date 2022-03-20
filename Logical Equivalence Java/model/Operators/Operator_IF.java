package model.Operators;

import java.util.Arrays;

public class Operator_IF extends Operator{
    private Unit leftUnit;
    private Unit rightUnit;
    public Operator_IF(int size, Unit leftUnit, Unit rightUnit) {
        super(size);
        this.leftUnit = leftUnit;
        this.rightUnit = rightUnit;
        evaluate();
    }

    @Override
    public void evaluate() {
        boolean[] truthTable1 = leftUnit.getTruthTable();
        boolean[] truthTable2 = rightUnit.getTruthTable();
        int i = 0;
        while(i < size) {
            if (!truthTable1[i]) {
                truthTable[i] = true;
            } else if (truthTable2[i]) {
                truthTable[i] = true;
            } else {
                truthTable[i] = false;
            }
            i++;
        }
    }
    // @Override
    // public String toString() {
    //     return String.format("left: %s\nright: %s\n%s\n", leftUnit, rightUnit ,Arrays.toString(truthTable));
    // }
}
