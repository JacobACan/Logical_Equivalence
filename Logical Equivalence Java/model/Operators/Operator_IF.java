package model.Operators;

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
            if (!truthTable[i]) {
                truthTable[i] = true;
            } else if (truthTable2[i]) {
                truthTable1[i] = true;
            } else {
                truthTable[i] = false;
            }
            i++;
        }
    }
}
